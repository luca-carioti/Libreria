package com.uni.libreria.services;

import com.uni.libreria.entities.Artista;
import com.uni.libreria.repositories.ArtistaRepository;
import com.uni.libreria.support.exceptions.ArtistaAlreadyExistException;
import com.uni.libreria.support.exceptions.ArtistaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Artista>  findArtista(Artista artista) throws ArtistaNotFoundException {
        return artistaRepository.findByNomeContainingAndCognomeContaining(artista.getNome(), artista.getCognome());
    }







}
