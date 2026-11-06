package com.example.fg_backend.service;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {

@Value("${jwt.secret}")
private String secret;

private SecretKey getSigningKey()
{
    byte[] keyBytes = Base64.getDecoder().decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
}


public String generateToken(String email)
{
    return Jwts.builder()
    .subject(email)
    .issuedAt(new Date())
    .expiration(new Date(System.currentTimeMillis() + 86400000))
.signWith(getSigningKey())
.compact();
}

public String extractEmail(String token)
{
    return Jwts.parser()
    .verifyWith(getSigningKey())
    .build()
    .parseSignedClaims(token)
    .getPayload()
    .getSubject();
}

public boolean isTokenValid(String token)
{
    try{
        Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token);

        return true;
    }
    catch(Exception e)
    {
        return false;
    }

}

}
