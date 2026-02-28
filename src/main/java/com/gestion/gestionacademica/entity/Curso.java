package com.gestion.gestionacademica.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer creditos;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
}