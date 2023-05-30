package com.contactlist.contactlist.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String secret = "jake";
    public String generateToken(User user){
        System.out.println(secret);
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ContactList API").withSubject(user.getLogin()).withClaim("id",user.getId()).withExpiresAt(timeExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error to generate token JWT",exception);
        }
    }

    private Instant timeExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("ContactList API")
                    .build().verify(tokenJWT).getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("TOKEN JWT invalid or expired!");
        }
    }
}
