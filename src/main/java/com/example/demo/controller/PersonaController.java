package com.example.demo.controller;

import com.example.demo.dto.PersonaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Persona API")
public interface PersonaController {
    @ApiOperation("Add new data")
    PersonaDTO save(@RequestBody PersonaDTO persona);

    @ApiOperation("Find by Id")
    PersonaDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    List<PersonaDTO> list();

    @ApiOperation("Pagination request")
    Page<PersonaDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    PersonaDTO update(@RequestBody PersonaDTO dto, @PathVariable("id") Long id);
}