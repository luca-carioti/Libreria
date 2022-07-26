package com.uni.libreria.repositories;

import com.uni.libreria.entities.Attore;
import com.uni.libreria.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttoreRepository extends JpaRepository<Attore,Integer> {
    List<Attore> findByNomeContainingAndCognomeContaining(String nome, String cognome);
    Attore findById(int id);

    //FINITO
}
