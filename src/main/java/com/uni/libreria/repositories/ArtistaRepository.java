package com.uni.libreria.repositories;

import com.uni.libreria.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Integer> {
    List<Artista>  findByNomeContainingAndCognomeContaining(String nome, String cognome);
    Artista findById(int id);

    //FINITO
}
