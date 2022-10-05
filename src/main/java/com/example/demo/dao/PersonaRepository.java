package com.example.demo.dao;

import com.example.demo.model.Persona;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {
}