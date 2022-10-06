package com.example.demo.controller;

import com.example.demo.dto.DiosDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Dios API")
public interface DiosController {
    @ApiOperation("Add new data")
    DiosDTO save(@RequestBody DiosDTO dios);

    @ApiOperation("Find by Id")
    DiosDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    List<DiosDTO> list();

    @ApiOperation("Pagination request")
    Page<DiosDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    DiosDTO update(@RequestBody DiosDTO dto, @PathVariable("id") Long id);
}