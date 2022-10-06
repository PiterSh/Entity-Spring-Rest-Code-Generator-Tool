package com.example.demo.mapper;

import com.example.demo.dto.DiosDTO;
import com.example.demo.model.Dios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface DiosMapper extends GenericMapper<Dios, DiosDTO> {
    @Override
    @Mapping(target = "id")
    Dios asEntity(DiosDTO dto);
}