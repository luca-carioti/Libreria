package com.uni.libreria.services;

import com.uni.libreria.entities.Cliente;
import com.uni.libreria.repositories.ClienteRepository;
import com.uni.libreria.support.exceptions.UserMailAlreadyExistException;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Cliente registraCliente(Cliente cliente) throws UserMailAlreadyExistException {
        if (clienteRepository.existsByMail(cliente.getMail())) {
            throw new UserMailAlreadyExistException();
        }
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Cliente findById(int id){return clienteRepository.findById(id);}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Cliente findByMail(String mail){return clienteRepository.findByMailContaining(mail);}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Cliente> findByNomeAndCognome(String nome, String cognome){return clienteRepository.findByNomeContainingAndCognomeContaining(nome,cognome);}


    @Transactional(readOnly = true)
    public List<Cliente> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable=PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Page<Cliente> page= clienteRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCliente(int id){ clienteRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Cliente addCliente(Cliente cliente){return clienteRepository.save(cliente);}


    //TERMINATO
}
