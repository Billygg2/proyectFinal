package com.gestion.gestionacademica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.gestion.gestionacademica.entity.Estudiante;
import com.gestion.gestionacademica.service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteService.listar();
    }

    @GetMapping("/{id}")
    public Estudiante buscarPorId(@PathVariable Long id) {
        return estudianteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
    }
}