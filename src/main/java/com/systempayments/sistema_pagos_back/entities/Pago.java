package com.systempayments.sistema_pagos_back.entities;

import java.time.LocalDate;

import com.systempayments.sistema_pagos_back.enums.PagoStatus;
import com.systempayments.sistema_pagos_back.enums.TypePagos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// @Entity es una anotación que indica que la clase es una entidad de la base de datos
@Entity
// @Builder es una anotación que indica que la clase es un constructor de
// objetos(CREA INSTANCIAS)
@Builder
// @Data es una anotación que indica que la clase tiene getters y setters
@Data
// @NoArgsConstructor es una anotación que indica que la clase tiene un
// constructor
@NoArgsConstructor
// @AllArgsConstructor es una anotación que indica que la clase tiene un
// constructor con todos los argumentos
@AllArgsConstructor

public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private LocalDate fecha;
    private Double monto;
    private TypePagos type;
    private PagoStatus status;
    private String file;

    // @ManyToOne Define una relación muchos a uno entre entidades
    @ManyToOne
    private Estudiante estudiante;
}
