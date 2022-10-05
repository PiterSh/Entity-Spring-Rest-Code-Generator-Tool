package com.example.demo.util;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.model.Persona;

import java.util.Arrays;
import java.util.List;

public class PersonaBuilder {

    public static List<PersonaDTO> getListDTO() {
        return Arrays.asList(
                new PersonaDTO(1L, 15, "azul", "Paco" ),
                new PersonaDTO(2L, 10, "verde", "Luis" ),
                new PersonaDTO(3L, 20, "negro", "Dori" ),
                new PersonaDTO(4L, 13, "rosa", "Vicente" )
        );
    }

    public static List<Persona> getListEntities() {
        return Arrays.asList(
                new Persona(1L, 15, "azul", "Paco" ),
                new Persona(2L, 10, "verde", "Luis" ),
                new Persona(3L, 20, "negro", "Dori" ),
                new Persona(4L, 13, "rosa", "Vicente" )
        );
    }

    public static PersonaDTO getDTO() {
        return new PersonaDTO(1L, 15, "azul", "Paco" );
    }

    public static Persona getEntity() {
        return new Persona(1L, 15, "azul", "Paco" );
    }
}
