package com.uni.libreria.services;

import com.uni.libreria.entities.Reparto;
import com.uni.libreria.repositories.RepartoRepository;
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
public class RepartoService {
    @Autowired
    private RepartoRepository repartoRepository;

    @Transactional(readOnly = true)
    public Reparto findById(int id){return repartoRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Reparto> findAll(int pageNumber, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Reparto> page= repartoRepository.findAll(pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeReparto(int id){repartoRepository.deleteById(id);}

    @Transactional(propagation = Propagation.REQUIRED)
    public Reparto addReparto(Reparto reparto){return repartoRepository.save(reparto);}

    //TERMINATO


}
