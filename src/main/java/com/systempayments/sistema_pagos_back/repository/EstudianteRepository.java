package com.systempayments.sistema_pagos_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempayments.sistema_pagos_back.entities.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    Estudiante findByCodigo(String codigo);

    List<Estudiante> findByProgramaId(String programaId);

}
