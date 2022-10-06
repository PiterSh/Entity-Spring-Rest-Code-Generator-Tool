package com.example.demo.dao;

import com.example.demo.model.Dios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiosRepository extends PagingAndSortingRepository<Dios, Long> {
}