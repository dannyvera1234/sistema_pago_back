package com.systempayments.sistema_pagos_back.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.systempayments.sistema_pagos_back.dto.GeneryDto;
import com.systempayments.sistema_pagos_back.entities.Estudiante;
import com.systempayments.sistema_pagos_back.entities.Pago;
import com.systempayments.sistema_pagos_back.repository.EstudianteRepository;
import com.systempayments.sistema_pagos_back.repository.PagoReposity;
import com.systempayments.sistema_pagos_back.services.PagoService;
import com.systempayments.sistema_pagos_back.ultils.ResponseUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/api") // para agrupar rutas opcionalmente
public class PagoController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PagoReposity pagoReposity;

    @Autowired
    private PagoService pagoService;

    @GetMapping("/estudiantes")
    public GeneryDto<List<Estudiante>> listarEstudiante() {
        return ResponseUtil.success(estudianteRepository.findAll(), "Estudiantes obtenidos exitosamente");
    }

    @GetMapping("/estudiante/{codigo}")
    public GeneryDto<Estudiante> listarEstudiantePorCodigo(@PathVariable String codigo) {
        return ResponseUtil.success(estudianteRepository.findByCodigo(codigo), "Estudiante obtenido exitosamente");
    }

    @GetMapping("/estudiantesPorPrograma")
    public GeneryDto<List<Estudiante>> listarEstudiantesPorPrograma(@RequestParam String programaId) {
        return ResponseUtil.success(estudianteRepository.findByProgramaId(programaId),
                "Estudiantes obtenidos exitosamente");

    }

    @GetMapping("/pagos")
    public GeneryDto<List<Pago>> listarPagos() {
        return ResponseUtil.success(pagoReposity.findAll(), "Pagos obtenidos exitosamente");
    }

    @GetMapping("/pagos/{id}")
    public GeneryDto<Pago> listarPagoPorId(@PathVariable Long id) {
        return ResponseUtil.success(pagoReposity.findById(id).get(), "Pago obtenido exitosamente");
    }

    @GetMapping("/Estudiante/{codigo}/pagos")
    public GeneryDto<List<Pago>> listarPagosPorCodigoEstudiante(@PathVariable String codigo) {
        return ResponseUtil.success(pagoReposity.findByEstudianteCodigo(codigo), "Pagos obtenidos exitosamente");
    }
}
