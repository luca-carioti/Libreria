package com.uni.libreria.services;

import com.uni.libreria.entities.*;
import com.uni.libreria.repositories.FilmRepository;
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
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Transactional(readOnly = true)
    public Film findById(int id){return filmRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Film> findByEditore(Editore editore, int pageNumber, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.findByEditore(editore, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Film> findByReparto(Reparto reparto, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.findByReparto(reparto, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Film> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita, Float prezzo,int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.advancedSearch(reparto, disponibilita,prezzo,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Film> findByAttore(Attore attore, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.findByAttore(attore, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Film> findByRegista(Regista regista, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.findByRegista(regista, pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFilm(int id){
        filmRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Film addFilm(Film film){return filmRepository.save(film);}

    @Transactional(readOnly = true)
    public List<Film> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Film> page= filmRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    //TERMINATO
}
