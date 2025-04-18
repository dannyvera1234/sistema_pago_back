package com.systempayments.sistema_pagos_back.ultils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.systempayments.sistema_pagos_back.dto.GeneryDto;

public class ResponseUtil {
    public static <T> GeneryDto<T> success(T data, String message) {
        GeneryDto<T> generyDto = new GeneryDto<>();
        generyDto.setData(data);
        generyDto.setMessage(message);
        generyDto.setCode(200);
        generyDto.setSuccess(true);
        generyDto.setTimesTamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return generyDto;
    }
}
