package com.uni.libreria.repositories;

import com.uni.libreria.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Integer> {
    List<Autore> findByNomeContainingAndCognomeContaining(String nome, String cognome);
    Autore findById(int id);

    //FINITO
}
