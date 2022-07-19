package com.uni.libreria.services;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Cliente;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.ProdottoInCarrello;
import com.uni.libreria.repositories.CarrelloRepository;
import com.uni.libreria.repositories.ProdottoInCarrelloRepository;
import com.uni.libreria.support.exceptions.CartAlreadyExistException;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private ProdottoInCarrelloRepository prodottoInCarrelloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Carrello findByCliente(Cliente cliente){return carrelloRepository.findByCliente(cliente);}

    @Transactional(readOnly = true)
    public Carrello findById(int id){return carrelloRepository.findById(id);}


    @Transactional(readOnly = true)
    public List<Carrello> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Carrello> page= carrelloRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCarrello(int id){carrelloRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Carrello addCarrello(Carrello carrello) throws CartAlreadyExistException, IllegalProductQuantityException {
        if(carrelloRepository.existsById(carrello.getId())){
            throw new CartAlreadyExistException();
        }
       return carrelloRepository.save(carrello);
    }



}
