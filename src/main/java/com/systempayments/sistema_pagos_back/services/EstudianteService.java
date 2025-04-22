package com.systempayments.sistema_pagos_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempayments.sistema_pagos_back.dto.GeneryDto;
import com.systempayments.sistema_pagos_back.entities.Estudiante;
import com.systempayments.sistema_pagos_back.repository.EstudianteRepository;
import com.systempayments.sistema_pagos_back.utils.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public GeneryDto<Estudiante> saveEstudiante(Estudiante estudiante) {
        return ResponseUtil.success(estudianteRepository.save(estudiante), "Estudiante creado exitosamente");
    }

    public GeneryDto<Estudiante> updateEstudiante(Estudiante estudiante) {
        return ResponseUtil.success(estudianteRepository.save(estudiante), "Estudiante actualizado exitosamente");
    }

    public GeneryDto<Long> deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
        return ResponseUtil.success(id, "Estudiante eliminado exitosamente");
    }

}
