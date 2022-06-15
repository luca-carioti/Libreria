package com.uni.libreria.services;

import com.uni.libreria.entities.Attore;
import com.uni.libreria.repositories.AttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttoreService {

    @Autowired AttoreRepository attoreRepository;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Attore> findAttore(Attore attore) {
        return attoreRepository.findByNomeContainingAndCognomeContaining(attore.getNome(),attore.getCognome());
    }

}
