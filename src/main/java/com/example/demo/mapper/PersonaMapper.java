package com.example.demo.mapper;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface PersonaMapper extends GenericMapper<Persona, PersonaDTO> {
    @Override
    @Mapping(target = "id")
    Persona asEntity(PersonaDTO dto);
}