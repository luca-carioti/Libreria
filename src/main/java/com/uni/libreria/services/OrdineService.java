package com.uni.libreria.services;

import com.uni.libreria.entities.Cliente;
import com.uni.libreria.entities.Ordine;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.ProdottoInOrdine;
import com.uni.libreria.repositories.ClienteRepository;
import com.uni.libreria.repositories.OrdineRepository;
import com.uni.libreria.repositories.ProdottoInOrdineRepository;
import com.uni.libreria.support.exceptions.ClienteNotFoundException;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableJpaRepositories
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ProdottoInOrdineRepository prodottoInOrdineRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(propagation= Propagation.REQUIRED)
    public Ordine addOrdine(Ordine ordine) throws IllegalProductQuantityException {
        Ordine aggiunto=ordineRepository.save(ordine);
        for(ProdottoInOrdine pio: aggiunto.getProdottoInOrdine()){
            pio.setOrdine(aggiunto); //ad ogni prodotto settiamo l'ordine
            ProdottoInOrdine pioJustAdded=prodottoInOrdineRepository.save(pio);
            entityManager.refresh(pioJustAdded); //aggiorniamo lo stato della tupla in questione
            Prodotto prodotto=pioJustAdded.getProdotto();
            int quantitaAttuale=prodotto.getQuantita()-pio.getQuantita();
            if(quantitaAttuale<0){
                throw new IllegalProductQuantityException();
            }
            prodotto.setQuantita(quantitaAttuale);
            entityManager.refresh(prodotto);
        }
        entityManager.refresh(aggiunto);
        return aggiunto;
    }

    @Transactional(readOnly = true)
    public List<Ordine> findByCliente(Cliente cliente, int pageNumber, int pageSize, String sortBy) throws ClienteNotFoundException {
        if(!clienteRepository.existsByMail(cliente.getMail())){
            throw new ClienteNotFoundException();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.findByCliente(cliente,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Ordine> findByData(Date data, int pageNumber, int pageSize, String sortBy) throws ClienteNotFoundException {

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.findByData(data,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Ordine> findAll(int pageNumber, int pageSize, String sortBy)  {

        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Ordine> findByStato(Ordine.Stato stato, int pageNumber,int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.findByStato(stato,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Ordine> findByMail(String mail, int pageNumber, int pageSize, String sortBy) throws ClienteNotFoundException{
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.findByMail(mail,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }


    @Transactional(readOnly = true)
    public List<Ordine> advancedSearch(Cliente cliente, Date data, Ordine.Stato stato,int pageNumber, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.advancedSearch(cliente,data,stato,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Ordine> ordiniInPeriodo(Date data_i, Date data_f,int pageNumber, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Ordine> page=ordineRepository.ordiniInPeriodo(data_i,data_f,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeOrdine(int id){ordineRepository.deleteById(id);}
}
