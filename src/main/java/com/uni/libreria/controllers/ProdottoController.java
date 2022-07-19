package com.uni.libreria.controllers;

import com.uni.libreria.entities.Cd;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.Reparto;
import com.uni.libreria.services.ProdottoService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/prodotti")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Prodotto ris=prodottoService.findById(id);
        if(ris==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy){
        List<Prodotto> prodotti=prodottoService.findAll(pageNumber,pageSize,sortBy);
        if(prodotti.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(prodotti, HttpStatus.OK);
    }

    @GetMapping("/search/by_nome")
    public ResponseEntity getByNome(@RequestParam(name = "nome") String nome){
        List<Prodotto> ris=prodottoService.findByNome(nome);
        if(ris.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/by_reparto")
    public ResponseEntity getByReparto(@RequestParam(name = "reparto") Reparto reparto,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Prodotto> ris=prodottoService.findByReparto(reparto,pageNumber,pageSize,sortBy);
        if(ris.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/by_prezzo_in_range")
    public ResponseEntity getByPrezzoInRange(@RequestParam(name = "base") float base,
                                             @RequestParam(name = "massimo") float massimo,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Prodotto> ris=prodottoService.findByPrezzoInRange(base,massimo,pageNumber,pageSize,sortBy);
        if(ris.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/by_disponibilita")
    public ResponseEntity getByDisponibilita(@RequestParam(name = "disponibilita") Prodotto.Disponibilita disponibilita,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Prodotto> ris=prodottoService.findByDisponibilita(disponibilita,pageNumber,pageSize,sortBy);
        if(ris.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/advanced")
    public ResponseEntity advanced(@RequestParam(value = "reparto",required = false) Reparto reparto,
                                   @RequestParam(value = "prezzo_min", required = false) Float prezzo_min,
                                             @RequestParam(value = "prezzo_max", required = false) Float prezzo_max,
                                   @RequestParam(value = "disponibilita", required = false) Prodotto.Disponibilita disponibilita,
                                             @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                             @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Prodotto> ris=prodottoService.advancedSearch(reparto,prezzo_min,prezzo_max,disponibilita,pageNumber,pageSize,sortBy);
        if(ris.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @PostMapping
    public void add(@RequestBody @Valid Prodotto prodotto){prodottoService.addProdotto(prodotto);}

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){prodottoService.removeProdotto(id);}




}
