package com.example.demo.controller.impl;

import com.example.demo.controller.EstudianteController;
import com.example.demo.dto.EstudianteDTO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/estudiante")
@RestController
public class EstudianteControllerImpl implements EstudianteController {

    private final EstudianteService estudianteService;
    private final EstudianteMapper estudianteMapper;

    public EstudianteControllerImpl(EstudianteService estudianteService, EstudianteMapper estudianteMapper) {
        this.estudianteService = estudianteService;
        this.estudianteMapper = estudianteMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstudianteDTO save(@RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteMapper.asEntity(estudianteDTO);
        return estudianteMapper.asDTO(estudianteService.save(estudiante));
    }

    @Override
    @GetMapping("/{id}")
    public EstudianteDTO findById(@PathVariable("id") Long id) {
        Estudiante estudiante = estudianteService.findById(id).orElse(null);
        return estudianteMapper.asDTO(estudiante);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        estudianteService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<EstudianteDTO> list() {
        return estudianteMapper.asDTOList(estudianteService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<EstudianteDTO> pageQuery(Pageable pageable) {
        Page<Estudiante> estudiantePage = estudianteService.findAll(pageable);
        List<EstudianteDTO> dtoList = estudiantePage
                .stream()
                .map(estudianteMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, estudiantePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public EstudianteDTO update(@RequestBody EstudianteDTO estudianteDTO, @PathVariable("id") Long id) {
        Estudiante estudiante = estudianteMapper.asEntity(estudianteDTO);
        return estudianteMapper.asDTO(estudianteService.update(estudiante, id));
    }
}