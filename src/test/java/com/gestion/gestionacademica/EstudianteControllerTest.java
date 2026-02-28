package com.gestion.gestionacademica;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.gestionacademica.controller.EstudianteController;
import com.gestion.gestionacademica.entity.Estudiante;
import com.gestion.gestionacademica.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private EstudianteService estudianteService;

    @SuppressWarnings("unused")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeListarEstudiantes() throws Exception {
        when(estudianteService.listar()).thenReturn(
                List.of(new Estudiante(1L, "Pedro", "pedro@mail.com", "Civil"))
        );

        mockMvc.perform(get("/api/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }
}