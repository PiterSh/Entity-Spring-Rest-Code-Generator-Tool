package com.example.demo.util;

import com.example.demo.dto.DiosDTO;
import com.example.demo.dto.EstudianteDTO;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.model.Dios;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Persona;

import java.util.Arrays;
import java.util.List;

public class DiosBuilder {
    public static List<DiosDTO> getListDTO() {
        return Arrays.asList(
                new DiosDTO(1L, new EstudianteDTO(), new PersonaDTO()),
                new DiosDTO(2L, new EstudianteDTO(), new PersonaDTO()),
                new DiosDTO(3L, new EstudianteDTO(), new PersonaDTO()),
                new DiosDTO(4L, new EstudianteDTO(), new PersonaDTO())
        );
    }

    public static List<Dios> getListEntities() {
        return Arrays.asList(
                new Dios(1L, new Estudiante(), new Persona()),
                new Dios(2L, new Estudiante(), new Persona()),
                new Dios(3L, new Estudiante(), new Persona()),
                new Dios(4L, new Estudiante(), new Persona())
        );
    }

    public static DiosDTO getDTO() {
        return new DiosDTO(1L, new EstudianteDTO(), new PersonaDTO());
    }

    public static Dios getEntity() {
        return new Dios(1L, new Estudiante(), new Persona());
    }
}
