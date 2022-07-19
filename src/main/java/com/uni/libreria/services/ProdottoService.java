package com.uni.libreria.services;

import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.Reparto;
import com.uni.libreria.repositories.ProdottoRepository;
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
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional(readOnly = true)
    public Prodotto findById(int id){return prodottoRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Prodotto> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Prodotto> page = prodottoRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> findByNome(String nome){return prodottoRepository.findByNomeContaining(nome);}

    @Transactional(readOnly = true)
    public List<Prodotto> findByReparto(Reparto reparto, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Prodotto> page = prodottoRepository.findByReparto(reparto,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> findByPrezzoInRange(float base,float massimo, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Prodotto> page = prodottoRepository.findByPrezzoInRange(base,massimo,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> findByDisponibilita(Prodotto.Disponibilita disponibilita, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Prodotto> page = prodottoRepository.findByDisponibilita(disponibilita,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> advancedSearch(Reparto reparto, Float prezzo_min, Float prezzo_max, Prodotto.Disponibilita disponibilita, int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Prodotto> page = prodottoRepository.advancedSearch(reparto,prezzo_min,prezzo_max,disponibilita,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{

            return new ArrayList<>();
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void removeProdotto(int id){prodottoRepository.deleteById(id);}

    @Transactional(propagation=Propagation.REQUIRED)
    public Prodotto addProdotto(Prodotto prodotto){return prodottoRepository.save(prodotto);}



}
