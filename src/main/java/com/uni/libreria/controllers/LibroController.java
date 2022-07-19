package com.uni.libreria.controllers;

import com.uni.libreria.entities.*;
import com.uni.libreria.services.LibroService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/libri")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping
    public void add(@RequestBody @Valid Libro libro){libroService.addLibro(libro);}

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){libroService.removeLibro(id);}

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Libro libro=libroService.findById(id);
        if(libro==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(libro,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "data") String sortBy){
        List<Libro> libri=libroService.findAll(pageNumber,pageSize,sortBy);
        if(libri.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(libri, HttpStatus.OK);
    }

    @GetMapping("/search/by_reparto")
    public ResponseEntity getByReparto(@RequestParam(name = "reparto") Reparto reparto,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
        List<Libro> libri=libroService.findByReparto(reparto,pageNumber,pageSize,sortBy);
        if(libri.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(libri, HttpStatus.OK);
    }

    @GetMapping("/search/by_editore")
    public ResponseEntity getByEditore(@RequestParam(name = "editore") Editore editore,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "data") String sortBy){
        List<Libro> libri=libroService.findByEditore(editore,pageNumber,pageSize,sortBy);
        if(libri.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(libri, HttpStatus.OK);
    }

    @GetMapping("/search/advanced")
    public ResponseEntity advancedSearch(@RequestParam(value = "reparto", required = false) Reparto reparto,
                                         @RequestParam(value = "disponibilita", required = false) Prodotto.Disponibilita disponibilita,
                                         @RequestParam(value = "prezzo", required = false) Float prezzo,
                                         @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                         @RequestParam(value = "pageSize",defaultValue = "15") int pageSize,
                                         @RequestParam(value = "sortBy",defaultValue = "data") String sortBy){
        List<Libro> libri=libroService.advancedSearch(reparto,disponibilita,prezzo,pageNumber,pageSize,sortBy);
        if(libri.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(libri, HttpStatus.OK);
    }

    @GetMapping("/search/by_autore")
    public ResponseEntity getByAutore(@RequestParam(name = "autore") Autore autore,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "data") String sortBy){
        List<Libro> libri=libroService.findByAutore(autore,pageNumber,pageSize,sortBy);
        if(libri.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(libri, HttpStatus.OK);
    }


}
