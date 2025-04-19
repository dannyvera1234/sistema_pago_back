package com.systempayments.sistema_pagos_back.services;

import java.net.URI;
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
import com.systempayments.sistema_pagos_back.utils.ResponseUtil;

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

        return ResponseUtil.success(pagoReposity.save(pago), "Pago creado exitosamente");
    }

    /**
     * Método que obtiene el archivo PDF asociado a un pago por su ID.
     *
     * @param pagoId ID del pago del cual se desea obtener el archivo.
     * @return Un arreglo de bytes que representa el contenido del archivo.
     * @throws Exception si ocurre un error al leer el archivo o no se encuentra el
     *                   pago.
     */
    public byte[] getArhivoPorId(Long pagoId) throws Exception {
        // Buscamos el pago en la base de datos a partir de su ID.
        // El método get() lanza una excepción si no se encuentra el pago.
        Pago pago = pagoReposity.findById(pagoId).get();

        // Obtenemos la ruta del archivo desde el campo 'file' del objeto Pago.
        // Luego convertimos esa ruta a un objeto Path para poder leer el archivo.
        return Files.readAllBytes(Paths.get(URI.create(pago.getFile())));
    }

    public GeneryDto<Pago> actualizarPagoPorStutus(PagoStatus status, Long id) {
        return ResponseUtil.success(pagoReposity.findById(id).get(), "Pago obtenido exitosamente");
    }

}
