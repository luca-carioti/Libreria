package com.uni.libreria.repositories;

import com.uni.libreria.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {


    Film findById(int id);

    Page<Film> findByEditore(Editore editore, Pageable pageable);

    @Query("select f from Film f inner join f.prodotto p " +
            "where (p.reparto=?1)")
    Page<Film> findByReparto(Reparto reparto,Pageable pageable);

    @Query(
            "select f from Film  f inner join f.prodotto p " +
                    "where (p.reparto = ?1 or ?1 is null) and " +
                    "(p.disponibilita=?2 or ?2 is null) and" +
                    "(p.prezzo <= ?3 or ?3 is null)"
    )
    Page<Film> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita, Float prezzo, Pageable pageable);

    @Query("select f from Film f join f.attori a where a=?1")
    Page<Film> findByAttore(Attore a, Pageable pageable);

    @Query("select f from Film f join f.registi r where r=?1")
    Page<Film> findByRegista(Regista regista, Pageable pageable);

}
