package com.uni.libreria.repositories;

import com.uni.libreria.entities.FilmInCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmInCarrelloRepository extends JpaRepository<FilmInCarrello, Integer> {
}
