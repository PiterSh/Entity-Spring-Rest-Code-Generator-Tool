package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends PagingAndSortingRepository<Estudiante, Long> {
}