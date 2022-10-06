package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiosDTO extends AbstractDTO<Long> {
    private Long id;
    private EstudianteDTO estudiante;
    private PersonaDTO persona;

}