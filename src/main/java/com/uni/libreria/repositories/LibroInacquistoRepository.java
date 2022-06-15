package com.uni.libreria.repositories;

import com.uni.libreria.entities.LibroInAquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroInacquistoRepository extends JpaRepository<LibroInAquisto,Integer> {
}
