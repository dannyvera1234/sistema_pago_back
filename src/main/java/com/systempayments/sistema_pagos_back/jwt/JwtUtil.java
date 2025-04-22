package com.systempayments.sistema_pagos_back.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component // ✅ Marca la clase como un componente para que Spring pueda inyectarla como
           // Bean
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // en segundos

    // ✅ Genera un token JWT a partir del nombre de usuario
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username) // Establece el sujeto (usuario)
                .issuedAt(new Date()) // Fecha de creación del token
                .expiration(new Date(new Date().getTime() + expiration * 1000)) // Fecha de expiración
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))) // Llave para validar la firma
                .compact(); // Genera el JWT como cadena
    }

    // ✅ Verifica si el token es valido
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ✅ Verifica si el token ha expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ✅ Extrae la fecha de expiración del token
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // ✅ Extrae todos los claims (datos) del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String jwt, Object userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }
}
