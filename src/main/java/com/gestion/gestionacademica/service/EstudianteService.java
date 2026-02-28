package com.gestion.gestionacademica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gestion.gestionacademica.repository.EstudianteRepository;
import com.gestion.gestionacademica.entity.Estudiante;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }
}