package com.uni.libreria.repositories;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.ProdottoInCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoInCarrelloRepository extends JpaRepository<ProdottoInCarrello,Integer> {
    @Query("select p from ProdottoInCarrello  p where " +
            "p.prodotto=?1 and p.carrello=?2")
    ProdottoInCarrello findByProdottoAndCarrello(Prodotto prodotto, Carrello carrello);
    ProdottoInCarrello findById(int id);
    @Query("select pic from ProdottoInCarrello pic  where " +
            "pic.carrello = ?1")
    List<ProdottoInCarrello> findByCarrello(Carrello carrello);
}
