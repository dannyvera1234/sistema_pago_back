package com.systempayments.sistema_pagos_back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String idetificacion;
    private String password;
    private String email;
    private String name;
    private String celular;
    private String foto;

    // @OneToOne(fetch = FetchType.EAGER, optional = false) = Sirve para indicar que
    // la relación es de uno a uno
    // @JoinColumn(name = "role_id", nullable = false) = Sirve para indicar que la
    // columna role_id es la llave foránea
    // y que no puede ser nula y que es obligatoria
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
