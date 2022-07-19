package com.uni.libreria.controllers;

import com.uni.libreria.entities.Attore;
import com.uni.libreria.services.AttoreService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/attori")
public class AttoreController {
    @Autowired
    private AttoreService attoreService;

    @PostMapping
    public void add(@RequestBody @Valid Attore attore){attoreService.addAttore(attore);}

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(value = "id") int id){
        Attore risultato=attoreService.findById(id);
        if(risultato==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(risultato, HttpStatus.OK);
    }

    @GetMapping("/search/by_nome_and_cognome")
    public ResponseEntity getByNomeAndCognome(@RequestParam(name = "nome", defaultValue = "") String nome,
                                              @RequestParam(name = "cognome", defaultValue = "") String cognome){
        List<Attore> attori=attoreService.findByNomeAndCognome(nome,cognome);
        if(attori.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza"), HttpStatus.OK);
        }
        return new ResponseEntity(attori, HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy){
        List<Attore> attori=attoreService.findAll(pageNumber,pageSize,sortBy);
        if(attori.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(attori, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam(name = "id") int id){
        attoreService.removeAttore(id);
        return new ResponseEntity(new ResponseMessage(id+" rimosso con successo!"), HttpStatus.OK);
    }


}
