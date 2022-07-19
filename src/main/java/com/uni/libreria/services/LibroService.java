package com.uni.libreria.services;

import com.uni.libreria.entities.*;
import com.uni.libreria.repositories.LibroRepository;
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
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Transactional(readOnly = true)
    public Libro findById(int id){return libroRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Libro> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Libro> page= libroRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> findByReparto(Reparto reparto, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Libro> page= libroRepository.findByReparto(reparto,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> findByEditore(Editore editore, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Libro> page= libroRepository.findByEditore(editore,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> findByAutore(Autore autore, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Libro> page= libroRepository.findByAutore(autore,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> advancedSearch(Reparto reparto, Prodotto.Disponibilita disponibilita,Float prezzo,  int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Libro> page= libroRepository.advancedSearch(reparto,disponibilita,prezzo,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void removeLibro(int id){libroRepository.deleteById(id);}

    @Transactional(propagation=Propagation.REQUIRED)
    public Libro addLibro(Libro libro){return libroRepository.save(libro);}



}
