package com.uni.libreria.repositories;

import com.uni.libreria.entities.Autore;
import com.uni.libreria.entities.Editore;
import com.uni.libreria.entities.Libro;
import com.uni.libreria.entities.Reparto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Integer> {

    Libro findById();
    Page<Libro> findByReparto(Reparto reparto, Pageable pageable);
    Page<Libro> findByNomeContaining(String nome, Pageable pageable);
    Page<Libro> findByEditore(Editore editore, Pageable pageable);

    @Query(
           "select l from Libro  l " +
                   "where (l.reparto = ?1 or ?1 is null) and " +
                   "(l.disponibilità=?2 or ?2 is null) and" +
                   "(l.prezzo <= ?3 or ?3 is null)"
    )
    Page<Libro> advancedSearch(Reparto reparto, String disponibilità, Integer prezzo);

    @Query("select l from Libro l join l.autori a where a=?1")
    Page<Libro> findByAutore(Autore autore, Pageable pageable);



}
