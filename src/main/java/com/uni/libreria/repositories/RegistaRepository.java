package com.uni.libreria.repositories;

import com.uni.libreria.entities.Regista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistaRepository extends JpaRepository<Regista,Integer> {
    Regista findByNomeContainingAndCognomeContaining(String nome, String cognome);
}
