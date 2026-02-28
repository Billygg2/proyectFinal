package com.gestion.gestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionacademica.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
}