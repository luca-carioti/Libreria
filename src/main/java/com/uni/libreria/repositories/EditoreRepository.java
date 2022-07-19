package com.uni.libreria.repositories;

import com.uni.libreria.entities.Editore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoreRepository extends JpaRepository<Editore, Integer> {
    Editore findByNomeContaining(String nome);
    Editore findById(int id);

    //TERMINATO
}
