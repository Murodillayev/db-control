package uz.pdp.dbcontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String username;
    private String password;
    private String email;

    // OTP and security controls // No GPT.
    private String otp;
    private Long otpExpiry;
    private Integer otpAttempts;
    private Integer resendCount;
    private Integer emailAttemptCount;
    private Long lockUntil;

    // Reset token for password reset flow // No GPT.
    private String resetToken;
    private Long resetTokenExpiry;

}
