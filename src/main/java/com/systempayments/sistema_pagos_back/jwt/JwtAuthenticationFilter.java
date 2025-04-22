package com.systempayments.sistema_pagos_back.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.systempayments.sistema_pagos_back.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Marca esta clase como un componente para que Spring la detecte
           // automáticamente
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Inyección de dependencias: herramienta para trabajar con JWTs
    private final JwtUtil jwtUtil;

    // Servicio que obtiene información del usuario
    private final UserService userService;

    // Constructor con inyección de dependencias
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Este método intercepta cada solicitud HTTP y valida si contiene un JWT
     * válido.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtenemos la cabecera "Authorization" de la petición
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // Verificamos si la cabecera existe y comienza con "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extraemos el token quitando el prefijo "Bearer "
            token = authHeader.substring(7);
            // Obtenemos el nombre de usuario desde el token
            username = jwtUtil.extractUsername(token);
        }

        // Si obtuvimos un usuario y no hay autenticación activa en el contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Obtenemos los detalles del usuario desde el servicio
            var userDetails = userService.loadUserByUsername(username);

            // Validamos si el token es correcto para ese usuario
            if (jwtUtil.validateToken(token, userDetails)) {
                // Creamos el token de autenticación
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities() // Roles o permisos del usuario
                );

                // Asociamos los detalles de la solicitud (IP, etc.) al token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecemos el usuario autenticado en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuamos con el resto de la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
