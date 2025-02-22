package com.example.project.security;

import com.example.project.Model.ApplicationUser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private long tokenValidity; // in milliseconds

    public String generateToken(ApplicationUser user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenValidity);
        return Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }


    public String getUsernameFromToken(String token) {
        System.out.println("Getting username from token " + token);
        String username =  getAllClaimsFromToken(token).getSubject();
        System.out.println("Username is " + username);
        return username;
    }

    private Claims getAllClaimsFromToken(String token) {
        System.out.println("Getting all claims from token");
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getAllClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
