package com.example.springboot.repository;

import com.example.springboot.model.Camp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampRepository extends CrudRepository<Camp,Integer> {
}
