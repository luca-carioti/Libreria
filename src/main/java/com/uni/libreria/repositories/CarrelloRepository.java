package com.uni.libreria.repositories;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
    List<Carrello> findAllByCliente(Cliente cliente);


}
