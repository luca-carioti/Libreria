package com.uni.libreria.services;

import com.uni.libreria.entities.Autore;
import com.uni.libreria.repositories.AutoreRepository;
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
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Transactional(readOnly = true)
    public Autore findById(int id){return autoreRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Autore> findByNomeAndCognome(String nome,String cognome){return autoreRepository.findByNomeContainingAndCognomeContaining(nome,cognome);}

    @Transactional(readOnly = true)
    public List<Autore> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Autore> page= autoreRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAutore(int id){autoreRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Autore addAutore(Autore autore){return autoreRepository.save(autore);}

    //TERMINATO
}
