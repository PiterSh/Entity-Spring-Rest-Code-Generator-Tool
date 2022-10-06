package com.example.demo.controller;

import com.example.demo.dto.EstudianteDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Estudiante API")
public interface EstudianteController {
    @ApiOperation("Add new data")
    EstudianteDTO save(@RequestBody EstudianteDTO estudiante);

    @ApiOperation("Find by Id")
    EstudianteDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    List<EstudianteDTO> list();

    @ApiOperation("Pagination request")
    Page<EstudianteDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    EstudianteDTO update(@RequestBody EstudianteDTO dto, @PathVariable("id") Long id);
}