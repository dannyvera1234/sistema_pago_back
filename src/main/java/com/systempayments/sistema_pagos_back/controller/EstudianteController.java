package com.systempayments.sistema_pagos_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systempayments.sistema_pagos_back.dto.EstudianteDto;
import com.systempayments.sistema_pagos_back.dto.GeneryDto;
import com.systempayments.sistema_pagos_back.entities.Estudiante;
import com.systempayments.sistema_pagos_back.repository.EstudianteRepository;
import com.systempayments.sistema_pagos_back.services.EstudianteService;
import com.systempayments.sistema_pagos_back.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @PostMapping("/estudiante/save")

    // public GeneryDto<Estudiante> saveEstudiante(EstudianteDto estudiante) {
    // return ResponseUtil.success(estudianteRepository.save(estudiante),
    // "Estudiante creado exitosamente");
    // }

    // @PutMapping("path/{id}")
    // public GeneryDto<Estudiante> updateEstudiante(EstudianteDto estudiante) {
    // return ResponseUtil.success(estudianteService.updateEstudiante(estudiante),
    // "Estudiante actualizado exitosamente");
    // }

    @GetMapping("/estudiantes")
    public GeneryDto<List<Estudiante>> listarEstudiante() {
        return ResponseUtil.success(estudianteRepository.findAll(), "Estudiantes obtenidos exitosamente");
    }

    @GetMapping("/estudiante/{codigo}")
    public GeneryDto<Estudiante> listarEstudiantePorCodigo(@PathVariable String codigo) {
        return ResponseUtil.success(estudianteRepository.findByCodigo(codigo), "Estudiante obtenido exitosamente");
    }

    @GetMapping("/estudiantesPorPrograma")
    public GeneryDto<List<Estudiante>> listarEstudiantesPorPrograma(@RequestParam(required = false) String programaId) {
        return ResponseUtil.success(estudianteRepository.findByProgramaId(programaId),
                "Estudiantes obtenidos exitosamente");
    }

}
