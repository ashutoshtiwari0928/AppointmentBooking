package com.homeopathy.homeopathyclinic.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTServiceImpl {

    String secretKey = "";
    SecretKey sk = null;
    public JWTServiceImpl() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            System.out.println(secretKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String generateToken(String email, String password) {
        Map<String,Object> claims = new HashMap<>();
        String jwtToken =  Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .and()
                .signWith(getKey())
                .compact();
        return jwtToken;
    }


    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try{
            Claims claims = extractClaims(token);
            return claims.getSubject().equals(userDetails.getUsername());
        }
        catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(sk)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public Date extractExpiration(String token){
        return extractClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(sk)
                .build()
                .parseSignedClaims(token).getPayload();
    }
}
