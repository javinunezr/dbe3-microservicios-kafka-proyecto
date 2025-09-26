package com.bancoxyz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoxyz.backend.model.entity.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
  
}
