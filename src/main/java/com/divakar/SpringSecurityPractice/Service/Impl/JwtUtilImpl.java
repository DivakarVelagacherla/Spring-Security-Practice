package com.divakar.SpringSecurityPractice.Service.Impl;

import com.divakar.SpringSecurityPractice.Service.JwtUtil;
import com.divakar.SpringSecurityPractice.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtilImpl implements JwtUtil {

    private final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private final String SECURITY_KEY = "35613106022E3B221A250CD0F334448ECEB8F0A2E09E683DAA8F30A0AD6C11C1";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigninKey() {
        byte[] key = Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return (userDetails.getUsername().equals(userName) && isTokenExpired(token));
    }
}
