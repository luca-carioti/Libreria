package com.uni.libreria.services;

import com.uni.libreria.entities.Regista;
import com.uni.libreria.repositories.RegistaRepository;
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
public class RegistaService {

    @Autowired
    private RegistaRepository registaRepository;

    @Transactional(readOnly = true)
    public List<Regista> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Regista> page= registaRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public Regista findById(int id){return registaRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Regista> findByNomeAndCognome(String nome, String cognome){return registaRepository.findByNomeContainingAndCognomeContaining(nome,cognome);}

    @Transactional(propagation= Propagation.REQUIRED)
    public void removeRegista(int id){registaRepository.deleteById(id);}

    @Transactional(propagation=Propagation.REQUIRED)
    public Regista addRegista(Regista regista){return registaRepository.save(regista);}

    //TERMINATO

}
