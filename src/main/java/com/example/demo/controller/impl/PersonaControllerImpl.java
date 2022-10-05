package com.example.demo.controller.impl;

import com.example.demo.controller.PersonaController;
import com.example.demo.dto.PersonaDTO;
import com.example.demo.mapper.PersonaMapper;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/persona")
@RestController
public class PersonaControllerImpl implements PersonaController {
    private final PersonaService personaService;
    private final PersonaMapper personaMapper;

    public PersonaControllerImpl(PersonaService personaService, PersonaMapper personaMapper) {
        this.personaService = personaService;
        this.personaMapper = personaMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaDTO save(@RequestBody PersonaDTO personaDTO) {
        Persona persona = personaMapper.asEntity(personaDTO);
        return personaMapper.asDTO(personaService.save(persona));
    }

    @Override
    @GetMapping("/{id}")
    public PersonaDTO findById(@PathVariable("id") Long id) {
        Persona persona = personaService.findById(id).orElse(null);
        return personaMapper.asDTO(persona);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personaService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<PersonaDTO> list() {
        return personaMapper.asDTOList(personaService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<PersonaDTO> pageQuery(Pageable pageable) {
        Page<Persona> personaPage = personaService.findAll(pageable);
        List<PersonaDTO> dtoList = personaPage
                .stream()
                .map(personaMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, personaPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public PersonaDTO update(@RequestBody PersonaDTO personaDTO, @PathVariable("id") Long id) {
        Persona persona = personaMapper.asEntity(personaDTO);
        return personaMapper.asDTO(personaService.update(persona, id));
    }
}