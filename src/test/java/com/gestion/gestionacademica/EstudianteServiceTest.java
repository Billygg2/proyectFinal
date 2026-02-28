package com.gestion.gestionacademica;

import com.gestion.gestionacademica.entity.Estudiante;
import com.gestion.gestionacademica.repository.EstudianteRepository;
import com.gestion.gestionacademica.service.EstudianteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void debeGuardarEstudiante() {
        Estudiante estudiante = new Estudiante(null, "Juan", "juan@mail.com", "Ingenieria");

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante resultado = estudianteService.guardar(estudiante);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void debeListarEstudiantes() {
        when(estudianteRepository.findAll()).thenReturn(List.of(
                new Estudiante(1L, "Ana", "ana@mail.com", "Sistemas")
        ));

        List<Estudiante> lista = estudianteService.listar();

        assertEquals(1, lista.size());
        verify(estudianteRepository, times(1)).findAll();
    }
}