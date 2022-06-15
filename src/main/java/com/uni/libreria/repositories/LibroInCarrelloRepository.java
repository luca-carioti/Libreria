package com.uni.libreria.repositories;

import com.uni.libreria.entities.LibroInCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroInCarrelloRepository extends JpaRepository<LibroInCarrello,Integer> {
}
