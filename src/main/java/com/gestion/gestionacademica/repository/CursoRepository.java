package com.gestion.gestionacademica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionacademica.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}