package com.uni.libreria.repositories;

import com.uni.libreria.entities.Regista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistaRepository extends JpaRepository<Regista,Integer> {
    List<Regista> findByNomeContainingAndCognomeContaining(String nome, String cognome);
    Regista findById(int id);
}
