package com.uni.libreria.repositories;

import com.uni.libreria.entities.FilmInAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmInAcquistoRepository extends JpaRepository<FilmInAcquisto,Integer> {
}
