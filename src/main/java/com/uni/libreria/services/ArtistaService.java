package com.uni.libreria.services;

import com.uni.libreria.entities.Artista;
import com.uni.libreria.entities.Attore;
import com.uni.libreria.entities.Cliente;
import com.uni.libreria.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional(readOnly = true)
    public Artista findById(int id){return artistaRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Artista> findByNomeAndCognome(String nome, String cognome){
        return artistaRepository.findByNomeContainingAndCognomeContaining(nome,cognome);
    }

    @Transactional(readOnly = true)
    public List<Artista> findAll(int pageNumber,int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Artista> page= artistaRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeArtista(int id){artistaRepository.deleteById(id);}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Artista addArtista(Artista artista){return artistaRepository.save(artista);}

    //TERMINATO
}
