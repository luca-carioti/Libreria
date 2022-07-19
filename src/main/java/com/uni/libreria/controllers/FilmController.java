package com.uni.libreria.controllers;

import com.uni.libreria.entities.*;
import com.uni.libreria.services.FilmService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping
    public void add(@RequestBody @Valid Film film){filmService.addFilm(film);}

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){filmService.removeFilm(id);}

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Film film=filmService.findById(id);
        if(film==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(film,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Film> film=filmService.findAll(pageNumber,pageSize,sortBy);
        if(film.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @GetMapping("/search/by_reparto")
    public ResponseEntity getByReparto(@RequestParam(name = "reparto") Reparto reparto,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Film> film=filmService.findByReparto(reparto,pageNumber,pageSize,sortBy);
        if(film.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @GetMapping("/search/by_editore")
    public ResponseEntity getByEditore(@RequestParam(name = "editore") Editore editore,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Film> film=filmService.findByEditore(editore,pageNumber,pageSize,sortBy);
        if(film.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @GetMapping("/search/advanced")
    public ResponseEntity advancedSearch(@RequestParam(value = "reparto", required = false) Reparto reparto,
                                         @RequestParam(value = "disponibilita", required = false) Prodotto.Disponibilita disponibilita,
                                         @RequestParam(value = "prezzo",required = false) Float prezzo,
                                         @RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
                                         @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                         @RequestParam(value = "sortBy",defaultValue = "dataPubblicazione") String sortBy){
        List<Film> films=filmService.advancedSearch(reparto,disponibilita,prezzo,pageNumber,pageSize,sortBy);
        if(films.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(films, HttpStatus.OK);
    }

    @GetMapping("/search/by_attore")
    public ResponseEntity getByAttore(@RequestParam(name = "attore") Attore attore,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Film> film=filmService.findByAttore(attore,pageNumber,pageSize,sortBy);
        if(film.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @GetMapping("/search/by_regista")
    public ResponseEntity getByRegista(@RequestParam(name = "regista") Regista regista,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Film> film=filmService.findByRegista(regista,pageNumber,pageSize,sortBy);
        if(film.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(film, HttpStatus.OK);
    }
}
