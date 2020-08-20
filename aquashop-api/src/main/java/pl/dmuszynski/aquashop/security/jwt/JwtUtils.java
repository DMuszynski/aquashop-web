package pl.dmuszynski.aquashop.security.jwt;

import pl.dmuszynski.aquashop.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${aquashop.jwtSecret}")
    private String jwtSecret;

    @Value("${aquashop.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return JWT.create()
            .withSubject(userPrincipal.getUsername())
            .withIssuedAt(new Date())
            .withIssuer("auth0")
            .withExpiresAt(new Date((new Date()).getTime() + jwtExpirationMs))
            .sign(algorithm);
    }

    public String getUserNameFromJwtToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance

            DecodedJWT jwt = verifier.verify(authToken);
            return true;
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            System.out.println("ZLY TOKEN");
        }


//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }

        return false;
    }
}