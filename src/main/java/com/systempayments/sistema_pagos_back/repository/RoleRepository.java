package com.systempayments.sistema_pagos_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempayments.sistema_pagos_back.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
