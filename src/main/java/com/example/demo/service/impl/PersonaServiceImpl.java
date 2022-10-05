package com.example.demo.service.impl;

import com.example.demo.dao.PersonaRepository;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository repository;

    public PersonaServiceImpl(PersonaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Persona save(Persona entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Persona> findAll() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    public Page<Persona> findAll(Pageable pageable) {
        Page<Persona> entityPage = repository.findAll(pageable);
        List<Persona> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Persona update(Persona entity, Long id) {
        Optional<Persona> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}