package com.uni.libreria.repositories;

import com.uni.libreria.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Integer> {

    Libro findById(int id);

    @Query(value="select * from Libro l inner join Prodotto p where l.id=p.libro and Reparto =?1", nativeQuery = true)
    Page<Libro> findByReparto(Reparto reparto, Pageable pageable);

    Page<Libro> findByEditore(Editore editore, Pageable pageable);

    @Query(
           "select l from Libro  l  join l.prodotto p where" +
                   "(p.reparto = ?1 or ?1 is null) and " +
                   "(p.disponibilita=?2 or ?2 is null) and" +
                   "(p.prezzo <= ?3 or ?3 is null)"
    )
    Page<Libro> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita, Float prezzo, Pageable pageable);

    @Query("select l from Libro l join l.autori a where a=?1")
    Page<Libro> findByAutore(Autore autore, Pageable pageable);
}
