package com.uni.libreria.services;

import com.uni.libreria.entities.*;
import com.uni.libreria.repositories.CdRepository;
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
public class CdService {
    @Autowired
    private CdRepository cdRepository;

    @Transactional(readOnly = true)
    public Cd findById(int id){return cdRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Cd> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Cd> page= cdRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Cd> findByReparto(Reparto reparto,int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Cd> page= cdRepository.findByReparto(reparto, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Cd> findByEditore(Editore editore, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Cd> page= cdRepository.findByEditore(editore, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Cd> findByArtista(Artista artista, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Cd> page= cdRepository.findByArtista(artista, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Cd> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita, Float prezzo,int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Cd> page= cdRepository.advancedSearch(reparto, disponibilita, prezzo, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCd(int id){cdRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Cd addCd(Cd cd) {return cdRepository.save(cd);}

}
