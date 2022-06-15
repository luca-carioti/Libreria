package com.uni.libreria.repositories;

import com.uni.libreria.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CdRepository extends JpaRepository<Cd,Integer> {
    Page<Cd> findByNameContaining(String name, Pageable pageable);
    Cd findById();
    Page<Cd> findByReparto(Reparto reparto, Pageable pageable);
    Page<Cd> findByEditore(Editore editore, Pageable pageable);

    @Query(
            "select c from Cd  c " +
                    "where (c.reparto = ?1 or ?1 is null) and " +
                    "(c.disponibilità=?2 or ?2 is null) and" +
                    "(c.prezzo <= ?3 or ?3 is null)"
    )
    Page<Cd> advancedSearch(Reparto reparto, String disponibilità, Integer prezzo);

    @Query("select c from Cd c join c.artisti a where a=?1")
    Page<Cd> findByArtista(Artista artista, Pageable pageable);


}
