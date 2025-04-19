package com.systempayments.sistema_pagos_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstudianteDto {
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
