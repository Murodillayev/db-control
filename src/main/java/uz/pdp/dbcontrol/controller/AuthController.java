package uz.pdp.dbcontrol.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.models.AuthUser;
import uz.pdp.dbcontrol.repos.AuthUserRepository;
import uz.pdp.dbcontrol.services.EmailNotifyService;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserRepository repository;
    private final EmailNotifyService emailService;

    // Basic login for demo purposes
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        Optional<AuthUser> byUsername = repository.findByUsername(req.getUsername());
        if (byUsername.isEmpty() || !byUsername.get().getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("message", "ok"));
    }

    // Registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        if (repository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
        }
        if (repository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
        }
        AuthUser u = new AuthUser();
        u.setName(req.getName());
        u.setUsername(req.getUsername());
        u.setPassword(req.getPassword());
        u.setEmail(req.getEmail());
        repository.save(u);
        return ResponseEntity.ok(Map.of("message", "registered"));
    }

    // Request OTP with 3 attempts then 30s lock
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody EmailReq req) {
        Optional<AuthUser> opt = repository.findByEmail(req.getEmail());
        if (opt.isEmpty()) {
            // Track attempt per email using a synthetic record? Minimal approach: no record -> respond with 401 and indication of remaining attempts not tracked.
            return ResponseEntity.status(404).body(Map.of("message", "Email not found"));
        }
        AuthUser user = opt.get();
        long now = System.currentTimeMillis();
        Integer emailAttempts = user.getEmailAttemptCount() == null ? 0 : user.getEmailAttemptCount();
        Long lockUntil = user.getLockUntil();
        if (lockUntil != null && lockUntil > now) {
            long secondsLeft = (lockUntil - now) / 1000;
            return ResponseEntity.status(429).body(Map.of("message", "Locked", "retryAfter", secondsLeft));
        }
        // If previously locked but time passed, reset
        if (lockUntil != null && lockUntil <= now) {
            user.setEmailAttemptCount(0);
            user.setLockUntil(null);
        }

        // Generate OTP and send
        String otp = emailService.generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(now + 5 * 60_000); // 5 minutes
        user.setOtpAttempts(0);
        user.setResendCount(0);
        repository.save(user);
        emailService.sendOtp(user);
        return ResponseEntity.ok(Map.of("message", "sent"));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody EmailReq req) {
        Optional<AuthUser> opt = repository.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("message", "Email not found"));
        AuthUser user = opt.get();
        long now = System.currentTimeMillis();
        if (user.getResendCount() == null) user.setResendCount(0);
        if (user.getResendCount() >= 2) {
            return ResponseEntity.status(429).body(Map.of("message", "Resend limit reached"));
        }
        String otp = emailService.generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(now + 5 * 60_000);
        user.setOtpAttempts(0);
        user.setResendCount(user.getResendCount() + 1);
        repository.save(user);
        emailService.sendOtp(user);
        return ResponseEntity.ok(Map.of("message", "resent", "remaining", 2 - user.getResendCount()));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyReq req) {
        Optional<AuthUser> opt = repository.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("message", "Email not found"));
        AuthUser user = opt.get();
        long now = System.currentTimeMillis();
        if (user.getOtp() == null || user.getOtpExpiry() == null || user.getOtpExpiry() < now) {
            return ResponseEntity.status(410).body(Map.of("message", "OTP expired"));
        }
        if (user.getOtpAttempts() == null) user.setOtpAttempts(0);
        if (!user.getOtp().equals(req.getOtp())) {
            user.setOtpAttempts(user.getOtpAttempts() + 1);
            repository.save(user);
            if (user.getOtpAttempts() >= 3) {
                // After 3 wrong OTPs, send back to login (frontend can handle based on 403)
                return ResponseEntity.status(403).body(Map.of("message", "Too many attempts"));
            }
            return ResponseEntity.status(400).body(Map.of("message", "Invalid OTP", "remaining", 3 - user.getOtpAttempts()));
        }
        // success
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(now + 10 * 60_000); // 10 minutes
        repository.save(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetReq req) {
        Optional<AuthUser> opt = repository.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("message", "Email not found"));
        AuthUser user = opt.get();
        long now = System.currentTimeMillis();
        if (user.getResetToken() == null || !user.getResetToken().equals(req.getToken()) || user.getResetTokenExpiry() == null || user.getResetTokenExpiry() < now) {
            return ResponseEntity.status(403).body(Map.of("message", "Invalid or expired token"));
        }
        user.setPassword(req.getNewPassword());
        // clear OTP and token state
        user.setOtp(null);
        user.setOtpExpiry(null);
        user.setOtpAttempts(0);
        user.setResendCount(0);
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        repository.save(user);
        return ResponseEntity.ok(Map.of("message", "password-updated"));
    }

    // DTOs
    @Data
    public static class LoginReq { private String username; private String password; }
    @Data
    public static class RegisterReq { private String name; private String username; private String password; private String email; }
    @Data
    public static class EmailReq { private String email; }
    @Data
    public static class VerifyReq { private String email; private String otp; }
    @Data
    public static class ResetReq { private String email; private String token; private String newPassword; }
}
