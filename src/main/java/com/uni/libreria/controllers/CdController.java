package com.uni.libreria.controllers;

import com.uni.libreria.entities.*;
import com.uni.libreria.services.CdService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cd")
public class CdController {
    @Autowired
    private CdService cdService;

    @PostMapping
    public void add(@RequestBody @Valid Cd cd){cdService.addCd(cd);}

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){cdService.removeCd(id);}

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Cd ris=cdService.findById(id);
        if(ris==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Cd> cd=cdService.findAll(pageNumber,pageSize,sortBy);
        if(cd.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(cd, HttpStatus.OK);
    }

    @GetMapping("/search/by_reparto")
    public ResponseEntity getByReparto(@RequestParam(name = "reparto") Reparto reparto,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Cd> cd=cdService.findByReparto(reparto,pageNumber,pageSize,sortBy);
        if(cd.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(cd, HttpStatus.OK);
    }

    @GetMapping("/search/by_editore")
    public ResponseEntity getByEditore(@RequestParam(name = "editore") Editore editore,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Cd> cd=cdService.findByEditore(editore,pageNumber,pageSize,sortBy);
        if(cd.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(cd, HttpStatus.OK);
    }

    @GetMapping("/search/by_artista")
    public ResponseEntity getByArtista(@RequestParam(name = "artista") Artista artista,
                                       @RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(name = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Cd> cd=cdService.findByArtista(artista,pageNumber,pageSize,sortBy);
        if(cd.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(cd, HttpStatus.OK);
    }

    @GetMapping("/search/advanced")
    public ResponseEntity advancedSearch(@RequestParam(value = "reparto", required = false) Reparto reparto,
                                         @RequestParam(value = "disponibilita", required = false)Prodotto.Disponibilita disponibilita,
                                         @RequestParam(value = "prezzo", required = false) Float prezzo,
                                         @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                         @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = "dataPubblicazione") String sortBy){
        List<Cd> cd=cdService.advancedSearch(reparto,disponibilita,prezzo,pageNumber,pageSize,sortBy);
        if(cd.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(cd, HttpStatus.OK);
    }
}
