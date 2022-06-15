package com.uni.libreria.repositories;

import com.uni.libreria.entities.CdInAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdInAcquistoRepository extends JpaRepository<CdInAcquisto, Integer> {
}
