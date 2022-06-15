package com.uni.libreria.repositories;

import com.uni.libreria.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
    Acquisto findById(int id);
    Page<Acquisto> findByCliente(Cliente cliente);
    List<Acquisto> findByClienteAndData(Cliente cliente, Date data);

    @Query("select a from Acquisto a join a.cd c where c=?1")
    Page<Acquisto> findByCd(Cd cd) ;

    @Query("select a from Acquisto a join a.film f where f=?1")
    Page<Acquisto> findByFilm(Film film);

    @Query("select a from Acquisto a join a.libri l where l=?1")
    Page<Acquisto> findByLibro(Libro libro);


}
