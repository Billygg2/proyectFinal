package com.gestion.gestionacademica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionacademica.entity.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}