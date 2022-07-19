package com.uni.libreria.repositories;

import com.uni.libreria.entities.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    boolean existsByMail(String mail);
    Cliente findById(int id);
    Cliente findByMailContaining(String mail);
    List<Cliente> findByNomeContainingAndCognomeContaining(String nome, String cognome);


}
