package com.uni.libreria.controllers;

import com.uni.libreria.entities.Artista;
import com.uni.libreria.entities.Autore;
import com.uni.libreria.services.ArtistaService;
import com.uni.libreria.services.AutoreService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/autori")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @PostMapping
    public void add(@RequestBody @Valid Autore autore){autoreService.addAutore(autore);}

    @GetMapping("/all")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Autore> autori= autoreService.findAll(pageNumber,pageSize,sortBy);
        if(autori.size()<=0){
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato trovato!"), HttpStatus.OK);
        }
        return new ResponseEntity(autori, HttpStatus.OK);
    }

    @GetMapping( "/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Autore autore = autoreService.findById(id);
        if(autore==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corispondenza con questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(autore, HttpStatus.OK);
    }

    @GetMapping("/search/by_nome_and_cognome")
    public ResponseEntity getByNomeAndCognome (@RequestParam(name = "nome" , defaultValue = "") String nome , @RequestParam(name = "cognome", defaultValue = "") String cognome){
        List<Autore> autori= autoreService.findByNomeAndCognome(nome,cognome);
        if(autori.size()<=0){
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(autori, HttpStatus.OK);
    }

    @DeleteMapping
    public void delete (@RequestParam(name = "id") int id){autoreService.removeAutore(id);}
}
