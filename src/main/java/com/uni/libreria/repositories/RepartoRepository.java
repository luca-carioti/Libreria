package com.uni.libreria.repositories;

import com.uni.libreria.entities.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartoRepository extends JpaRepository<Reparto,Integer> {
}
