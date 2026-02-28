package com.gestion.gestionacademica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionacademica.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}