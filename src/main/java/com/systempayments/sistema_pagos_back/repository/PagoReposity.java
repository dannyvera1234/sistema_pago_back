package com.systempayments.sistema_pagos_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempayments.sistema_pagos_back.entities.Pago;
import com.systempayments.sistema_pagos_back.enums.PagoStatus;
import com.systempayments.sistema_pagos_back.enums.TypePagos;

@Repository
public interface PagoReposity extends JpaRepository<Pago, String> {

    List<Pago> findByEstudianteCodigo(String id);

    List<Pago> findByStatus(PagoStatus status);

    List<Pago> findByType(TypePagos type);
}
