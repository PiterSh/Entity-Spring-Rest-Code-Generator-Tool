package com.example.demo.service.impl;

import com.example.demo.dao.DiosRepository;
import com.example.demo.model.Dios;
import com.example.demo.service.DiosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiosServiceImpl implements DiosService {
    private final DiosRepository repository;

    public DiosServiceImpl(DiosRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dios save(Dios entity) {
        return repository.save(entity);
    }

    @Override
    public List<Dios> save(List<Dios> entities) {
        return (List<Dios>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Dios> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Dios> findAll() {
        return (List<Dios>) repository.findAll();
    }

    @Override
    public Page<Dios> findAll(Pageable pageable) {
        Page<Dios> entityPage = repository.findAll(pageable);
        List<Dios> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Dios update(Dios entity, Long id) {
        Optional<Dios> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}