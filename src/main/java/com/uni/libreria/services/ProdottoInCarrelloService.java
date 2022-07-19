package com.uni.libreria.services;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.ProdottoInCarrello;
import com.uni.libreria.repositories.ProdottoInCarrelloRepository;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProdottoInCarrelloService {
    @Autowired
    private ProdottoInCarrelloRepository prodottoInCarrelloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addProdotto(ProdottoInCarrello pic) throws IllegalProductQuantityException {
        Prodotto prodotto=pic.getProdotto();
        int quantitaAttuale=prodotto.getQuantita()-pic.getQuantita();
        ProdottoInCarrello check=prodottoInCarrelloRepository.findByProdottoAndCarrello(pic.getProdotto(),pic.getCarrello());
        if(check!=null){
            int quantitaPrecedente=check.getQuantita();
            if(quantitaAttuale<0){
                throw new IllegalProductQuantityException();
            }
            check.setQuantita(quantitaPrecedente+pic.getQuantita());
        }else{
            prodottoInCarrelloRepository.save(pic);
            prodotto.setQuantita(quantitaAttuale);
        }
    }

    @Transactional
    public void removeProdotto(int id){
        ProdottoInCarrello pic=prodottoInCarrelloRepository.findById(id);
        Prodotto riferito=pic.getProdotto();
        riferito.setQuantita(pic.getQuantita()+riferito.getQuantita());
        entityManager.refresh(riferito);
        prodottoInCarrelloRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProdottoInCarrello> getByCarrello(Carrello carrello){
        return prodottoInCarrelloRepository.findByCarrello(carrello);
    }

    @Transactional(readOnly = true)
    public ProdottoInCarrello getByProdottoAndCarrello(Prodotto prodotto, Carrello carrello){return prodottoInCarrelloRepository.findByProdottoAndCarrello(prodotto,carrello);}

    @Transactional(readOnly = true)
    public ProdottoInCarrello getById(int id){return prodottoInCarrelloRepository.findById(id);}
}
