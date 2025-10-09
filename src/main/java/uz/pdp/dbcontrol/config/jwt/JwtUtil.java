package uz.pdp.dbcontrol.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.TokenDto;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.property.AppProps;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final AppProps appProps;

    public TokenDto generateRefreshToken(AuthUser authUser) {
        Date tokenExpire = new Date(System.currentTimeMillis() + appProps.getRefreshTokenExp());
        String token = Jwts.builder()
                .signWith(getSecretKey())
                .issuedAt(new Date())
//                .subject(authUser.getId())
                .subject(authUser.getUsername())
                .expiration(tokenExpire)
                .claims(Map.of())
                .compact();

        return TokenDto.builder()
                .token(token)
                .expiry(tokenExpire)
                .build();
    }

    public TokenDto generateAccessToken(AuthUser authUser) {

        Date tokenExpire = new Date(System.currentTimeMillis() + appProps.getAccessTokenExp());

        Map<String, Object> claims = Map.of(
                "userId", authUser.getId(),
                "authorities", new String[]{}
        );

        String token = Jwts.builder()
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .subject(authUser.getUsername())
                .expiration(tokenExpire)
                .claims(
                        claims
                )
                .compact();

        return TokenDto.builder()
                .token(token)
                .expiry(tokenExpire)
                .build();
    }

    public Claims validateTokenAndExtract(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        token = token.replaceFirst("Bearer ", "");
        Claims claims = extractClaims(token);
        if (claims.getExpiration().before(new Date()))
            throw new RuntimeException("Token is expired");

        return claims;

    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("bu_secret_key_u_kamida_32_xona_bolishi_shartdir".getBytes());
    }


}
