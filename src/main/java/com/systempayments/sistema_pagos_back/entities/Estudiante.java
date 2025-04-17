package com.systempayments.sistema_pagos_back.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity es una anotación que indica que la clase es una entidad de la base de datos
@Entity
// @Builder es una anotación que indica que la clase es un constructor de
// objetos(CREA INSTANCIAS)
@Builder
// @Data es una anotación que indica que la clase tiene getters y setters
@Data
// @NoArgsConstructor es una anotación que indica que la clase tiene un
// constructor sin argumentos
@NoArgsConstructor
// @AllArgsConstructor es una anotación que indica que la clase tiene un
// constructor con todos los argumentos
@AllArgsConstructor
public class Estudiante {

    @Id
    private String id;
    @Column(unique = true)
    private String codigo;
    private String programaId;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private String fechaNacimiento;
    private String sexo;
    private String direccion;
    private String foto;
    private String correo;
    private String celular;
    private String carrera;
}
