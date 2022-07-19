package com.uni.libreria.repositories;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Cliente;
import com.uni.libreria.entities.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
    Carrello findByCliente(Cliente cliente);
    Carrello findById(int id);
    Page<Carrello> findByDataCreazione(Date data, Pageable pageable);
    @Query("select p from Prodotto  p join p.prodottoInCarrello pic where " +
            "pic.carrello = ?1")
    Page<Prodotto> prodottoByCarrello(Carrello carrello, Pageable pageable);

}
