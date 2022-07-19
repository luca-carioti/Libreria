package com.uni.libreria.repositories;

import com.uni.libreria.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CdRepository extends JpaRepository<Cd,Integer> {


    Cd findById(int id);
    @Query("select c from Cd c inner join c.prodotto p where (p.reparto=?1)")
    Page<Cd> findByReparto(Reparto reparto, Pageable pageable);
    Page<Cd> findByEditore(Editore editore, Pageable pageable);

    @Query(
            "select c from Cd  c inner join c.prodotto p " +
                    "where (p.reparto = ?1 or ?1 is null) and " +
                    "(p.disponibilita=?2 or ?2 is null) and" +
                    "(p.prezzo <= ?3 or ?3 is null)"
    )
    Page<Cd> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita, Float prezzo, Pageable pageable);

    @Query("select c from Cd c join c.artisti a where a=?1")
    Page<Cd> findByArtista(Artista artista, Pageable pageable);

}
