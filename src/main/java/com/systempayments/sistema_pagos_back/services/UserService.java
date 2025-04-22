package com.systempayments.sistema_pagos_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.systempayments.sistema_pagos_back.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service // Marca la clase como un servicio de Spring para que se maneje como un
         // componente de lógica de negocio
@Transactional // Todas las operaciones dentro de esta clase serán transaccionales
public class UserService implements UserDetailsService {

    @Autowired // Inyección del repositorio de usuarios
    private UserRepository userRepository;

    /**
     * Método sobrescrito del UserDetailsService de Spring Security
     * Se encarga de buscar un usuario por su nombre y devolver un objeto
     * UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Busca al usuario por nombre de usuario en la base de datos
        var user = userRepository.findByUserName(userName);

        // Si no se encuentra el usuario, lanza una excepción
        if (user == null) {
            throw new UsernameNotFoundException("User '" + userName + "' not found");
        }

        // Crea una autoridad (rol) basada en el rol del usuario
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().toString());

        // Retorna un objeto UserDetails de Spring con username, password y roles
        return new org.springframework.security.core.userdetails.User(
                user.getIdetificacion(),
                user.getPassword(),
                java.util.Collections.singletonList(authority));
    }

}
