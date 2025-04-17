package com.systempayments.sistema_pagos_back.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.systempayments.sistema_pagos_back.dto.GeneryDto;
import com.systempayments.sistema_pagos_back.dto.NewPagoDto;
import com.systempayments.sistema_pagos_back.entities.Estudiante;
import com.systempayments.sistema_pagos_back.entities.Pago;
import com.systempayments.sistema_pagos_back.enums.PagoStatus;
import com.systempayments.sistema_pagos_back.repository.EstudianteRepository;
import com.systempayments.sistema_pagos_back.repository.PagoReposity;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoReposity pagoReposity;
    @Autowired
    private EstudianteRepository estudianteRepository;

    public GeneryDto<Pago> savePago(MultipartFile file, NewPagoDto newPagoDto) throws Exception {
        /*
         * - Guardar el pago en la base de datos
         * - Creamos una ruta para guardar el archivo
         * - System.getProperty("user.home"): obtine la ruta del home del usuario en el
         * sistema
         * - Paths.get() : crea una ruta a partir de una cadena de texto
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pago");
        GeneryDto<Pago> generyDto = new GeneryDto<>();

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString();
        // creamos el path para guardar el archivo pdf
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pago", fileName + ".pdf");
        // copiamos el archivo a la ruta creada
        Files.copy(file.getInputStream(), filePath);

        Estudiante estudiante = estudianteRepository.findByCodigo(newPagoDto.getCodigoEstudiante());

        Pago pago = Pago.builder()
                .type(newPagoDto.getTypePago())
                .status(PagoStatus.CREADO)
                .fecha(newPagoDto.getDate())
                .estudiante(estudiante)
                .monto(newPagoDto.getMonto())
                .file(filePath.toUri().toString())
                .build();

        generyDto.setData(pagoReposity.save(pago));
        generyDto.setMessage("Pago saved successfully");
        generyDto.setSuccess(true);
        generyDto.setCode(201);
        generyDto.setTimesTamp(System.currentTimeMillis() + "");
        return generyDto;
    }

}
