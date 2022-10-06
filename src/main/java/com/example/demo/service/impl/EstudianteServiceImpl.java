package com.example.demo.service.impl;

import com.example.demo.dao.EstudianteRepository;
import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteServiceImpl(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estudiante save(Estudiante entity) {
        return repository.save(entity);
    }

    @Override
    public List<Estudiante> save(List<Estudiante> entities) {
        return (List<Estudiante>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Estudiante> findAll() {
        return (List<Estudiante>) repository.findAll();
    }

    @Override
    public Page<Estudiante> findAll(Pageable pageable) {
        Page<Estudiante> entityPage = repository.findAll(pageable);
        List<Estudiante> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Estudiante update(Estudiante entity, Long id) {
        Optional<Estudiante> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}