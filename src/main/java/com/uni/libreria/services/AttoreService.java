package com.uni.libreria.services;

import com.uni.libreria.entities.Attore;
import com.uni.libreria.repositories.AttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttoreService {

    @Autowired
    private AttoreRepository attoreRepository;

    @Transactional(readOnly = true)
    public Attore findById(int id){return attoreRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Attore> findByNomeAndCognome(String nome, String cognome){return attoreRepository.findByNomeContainingAndCognomeContaining(nome,cognome);}

    @Transactional(readOnly = true)
    public List<Attore> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Attore> page= attoreRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            System.out.println("La pagina non ha contenuto");
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAttore(int id){attoreRepository.deleteById(id);}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Attore addAttore(Attore attore){return attoreRepository.save(attore);} // non abbiamo vincoli di integrità su attore

    //TERMINATO


}
