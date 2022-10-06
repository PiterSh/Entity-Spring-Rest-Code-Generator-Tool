package com.example.demo.util;

import com.example.demo.dto.EstudianteDTO;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Genero;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class EstudianteBuilder {

    public static List<EstudianteDTO> getListDTO() {
        return Arrays.asList(
                new EstudianteDTO(1L, "Paco", 20, LocalDateTime.now(), Genero.MASCULINO),
                new EstudianteDTO(2L, "Ana", 25, LocalDateTime.now(), Genero.FEMENINO),
                new EstudianteDTO(3L, "Jose", 30, LocalDateTime.now(), Genero.MASCULINO),
                new EstudianteDTO(4L, "Luisa", 50, LocalDateTime.now(), Genero.FEMENINO)
        );
    }

    public static List<Estudiante> getListEntities() {
        return Arrays.asList(
                new Estudiante(1L, "Paco", 20, LocalDateTime.now(), Genero.MASCULINO),
                new Estudiante(2L, "Ana", 25, LocalDateTime.now(), Genero.FEMENINO),
                new Estudiante(3L, "Jose", 30, LocalDateTime.now(), Genero.MASCULINO),
                new Estudiante(4L, "Luisa", 50, LocalDateTime.now(), Genero.FEMENINO)
        );
    }

    public static EstudianteDTO getDTO() {
        return new EstudianteDTO(1L, "Paco", 20, LocalDateTime.now(), Genero.MASCULINO);
    }

    public static Estudiante getEntity() {
        return new Estudiante(1L, "Paco", 20, LocalDateTime.now(), Genero.MASCULINO);
    }

}
