package com.uni.libreria.controllers;

import com.uni.libreria.entities.Cd;
import com.uni.libreria.entities.Reparto;
import com.uni.libreria.services.RepartoService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reparti")
public class RepartoController {
    @Autowired
    private RepartoService repartoService;

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Reparto ris=repartoService.findById(id);
        if(ris==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy){
        List<Reparto> reparti=repartoService.findAll(pageNumber,pageSize,sortBy);
        if(reparti.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(reparti, HttpStatus.OK);
    }

    @PostMapping
    public void add(@RequestBody @Valid Reparto reparto){repartoService.addReparto(reparto);}

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){repartoService.removeReparto(id);}
}
