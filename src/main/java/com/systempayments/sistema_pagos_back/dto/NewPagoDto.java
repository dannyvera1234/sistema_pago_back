package com.systempayments.sistema_pagos_back.dto;

import java.time.LocalDate;

import com.systempayments.sistema_pagos_back.enums.TypePagos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPagoDto {
    private double monto;
    private TypePagos typePago;
    private LocalDate date;
    private String codigoEstudiante;
}
