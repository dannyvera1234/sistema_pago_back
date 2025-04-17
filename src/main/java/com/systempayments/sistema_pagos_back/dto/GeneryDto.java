package com.systempayments.sistema_pagos_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneryDto<T> {
    private String message;
    private boolean success;
    private int code;
    private String timesTamp;
    private T data;
}
