package com.uni.libreria.repositories;

import com.uni.libreria.entities.Cliente;
import com.uni.libreria.entities.Ordine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
    Page<Ordine> findByCliente(Cliente cliente, Pageable pageable);
    Page<Ordine> findByStato(Ordine.Stato stato, Pageable pageable);
    Page<Ordine> findByMail(String mail, Pageable pageable);
    Page<Ordine> findByData(Date data, Pageable pageable);

    @Query("select o from Ordine  o " +
            " where (o.cliente = ?1 or ?1 is null) " +
            "and (o.data= ?2 or ?2 is null) " +
            "and (o.stato = ?3 or ?3 is null)")
    Page<Ordine> advancedSearch(Cliente cliente, Date data, Ordine.Stato stato, Pageable pageable);


    @Query("select o from Ordine  o " +
            " where o.data>?1 and o.data<?2")
    Page<Ordine> ordiniInPeriodo(Date data_i, Date data_f, Pageable pageable);

}
