package com.uni.libreria.repositories;

import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.Reparto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Integer> {
    List<Prodotto> findByNomeContaining(String nome);
    Page<Prodotto> findByReparto(Reparto reparto, Pageable pageable);
    Page<Prodotto> findByDisponibilita(Prodotto.Disponibilita disponibilita, Pageable pageable);

    @Query("select p from Prodotto  p " +
            "where p.prezzo>=?1 and p.prezzo<=?2")
    Page<Prodotto> findByPrezzoInRange(float base, float massimo, Pageable pageable);

    Prodotto findById(int id);

    @Query("select p from Prodotto  p " +
            "where (p.reparto=?1 or ?1 is null) and (p.prezzo>=?2 and p.prezzo<=?3 or ?2 is null and ?3 is null) and (p.disponibilita=?4 or ?4 is null)")
    Page<Prodotto> advancedSearch(Reparto reparto, Float prezzo_min, Float prezzo_max, Prodotto.Disponibilita disponibilita, Pageable pageable);


}
