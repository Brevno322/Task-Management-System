package com.artmuh.taskmanagementsystem.util;

import com.artmuh.taskmanagementsystem.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;//24 hour


    @Value("${jwt.secret.access}")
    private String SECRET_KEY;


    public String generateAccessToken(User user) {

       // Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer("CodeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
//        return  Jwts.builder()
//                .setIssuer(String.format("%s,%s", user.getId(), user.getUsername()))
//                .setSubject("CodeJava")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
//                .signWith(key)
//                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {

//            JwtParser jwtParser =
//                    Jwts.parserBuilder()
//                            .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                            .build();
//
//            jwtParser.parseClaimsJws(token);
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);



            return true;

        } catch (ExpiredJwtException ex) {
            log.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("JWT is not supported", ex);
        } catch (SecurityException ex) {
            log.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
//        JwtParser parser = Jwts.parserBuilder()
//                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                .build();
//        return parser.parseClaimsJws(token).getBody();
         return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

    }


}
