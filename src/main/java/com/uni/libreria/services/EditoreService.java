package com.uni.libreria.services;

import com.uni.libreria.entities.Editore;
import com.uni.libreria.repositories.EditoreRepository;
import com.uni.libreria.support.exceptions.EditoreAlreadyExistException;
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
public class EditoreService {

    @Autowired
    private EditoreRepository editoreRepository;

    @Transactional(readOnly = true)
    public Editore findById(int id){return editoreRepository.findById(id);}

    @Transactional(readOnly = true)
    public Editore findByNome(String nome){return editoreRepository.findByNomeContaining(nome);}

    @Transactional(readOnly = true)
    public List<Editore> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Editore> page= editoreRepository.findAll(pageable);
        if(page.hasContent()) {
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeEditore(int id){editoreRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Editore addEditore(Editore editore) throws EditoreAlreadyExistException{
        if(editoreRepository.existsById(editore.getId())){
            throw new EditoreAlreadyExistException();
        }
        return editoreRepository.save(editore);
    }

    //TERMINATO

}
