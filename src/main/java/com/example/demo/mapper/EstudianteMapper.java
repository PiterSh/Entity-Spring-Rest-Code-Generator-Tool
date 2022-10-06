package com.example.demo.mapper;

import com.example.demo.dto.EstudianteDTO;
import com.example.demo.model.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface EstudianteMapper extends GenericMapper<Estudiante, EstudianteDTO> {
    @Override
    @Mapping(target = "id")
    Estudiante asEntity(EstudianteDTO dto);
}