package com.uni.libreria.repositories;

import com.uni.libreria.entities.CdInCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdInCarrelloRepository extends JpaRepository<CdInCarrello, Integer> {
}
