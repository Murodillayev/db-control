package uz.pdp.dbcontrol.config.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.dbcontrol.config.CustomUserDetails;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthUserRepository authUserRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        // check header(if is not public) and get token -> decode token -> get user data -> make userDetails -> put userDetails to SecurityContextHolder

        if (!isPublic(request) && request.getHeader("Authorization") != null) {
            String token = request.getHeader("Authorization");
            Claims claims = jwtUtil.validateTokenAndExtract(token);
            CustomUserDetails userDetails = prepareUserDetails(claims, true);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }


    private boolean isPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.asList(Utils.WHITE_LIST).contains(requestURI);
    }


    private List<GrantedAuthority> prepareAuthorities(AuthUser authUser) {


        List<GrantedAuthority> authorities = new ArrayList<>();

        AuthRole role = authUser.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + authUser.getRole()));

        List<AuthPermission> permissions = role.getPermissions();
        permissions.forEach(p -> {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        });
        return authorities;
    }


    private CustomUserDetails prepareUserDetails(Claims claims, boolean syncDb) {

        if (syncDb) {
            String username = claims.getSubject();
            AuthUser authUser = authUserRepository.findByUsernameAndDeletedFalse(username).orElseThrow(
                    () -> new BadCredentialsException("Invalid username or password")
            );
            List<GrantedAuthority> authorities = prepareAuthorities(authUser);
            return new CustomUserDetails(claims.getSubject(), null, authUser.getId(), authorities);

        } else {
            CustomUserDetails userDetails = new CustomUserDetails();
            userDetails.setUserId(claims.get("userId", String.class));
            userDetails.setUsername(claims.getSubject());
            userDetails.setAuthorities(prepareAuthorities(claims.get("authorities", String[].class)));
            return userDetails;
        }

    }

    private List<GrantedAuthority> prepareAuthorities(String[] auths) {
        return Arrays.stream(auths)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

}


// ROLE_ADMIN, create:user, update:user, delete:user

// hasRole('MANAGER')