package com.uni.libreria.repositories;

import com.uni.libreria.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Integer> {
    Autore findByNomeContainingAndCognomeContaing(String nome, String cognome);

}
