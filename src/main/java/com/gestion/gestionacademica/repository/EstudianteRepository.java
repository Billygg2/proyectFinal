package com.gestion.gestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionacademica.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}