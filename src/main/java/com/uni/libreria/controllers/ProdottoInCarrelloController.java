package com.uni.libreria.controllers;

import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Prodotto;
import com.uni.libreria.entities.ProdottoInCarrello;
import com.uni.libreria.services.ProdottoInCarrelloService;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/prodottiInCarrello")
public class ProdottoInCarrelloController {
    @Autowired
    private ProdottoInCarrelloService prodottoInCarrelloService;

    @GetMapping("/search/by_carrello")
    public ResponseEntity getByCarrello(@RequestParam("carrello") Carrello carrello,
                                        @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = "quantita") String sortBy){
        List<ProdottoInCarrello> risultati=prodottoInCarrelloService.getByCarrello(carrello);
        if(risultati.size()<=0){
            return new ResponseEntity(new ResponseMessage("Il carrello è vuoto"), HttpStatus.OK);
        }
        return new ResponseEntity(risultati, HttpStatus.OK);
    }

    //QUESTO CONTROLLER NON SERVE, IL METODO FA SOLO DA SUPPORTO AL SERVICE
    @GetMapping("/search/by_prodotto_and_carrello")
    public ResponseEntity getByProdottoAndCarrello(@RequestParam("prodotto") Prodotto prodotto,
                                        @RequestParam("carrello") Carrello carrello){
        ProdottoInCarrello risultato=prodottoInCarrelloService.getByProdottoAndCarrello(prodotto,carrello);
        if(risultato==null){
            return new ResponseEntity(new ResponseMessage("Il prdotto non è presente nel carrello"), HttpStatus.OK);
        }
        return new ResponseEntity(risultato, HttpStatus.OK);
    }

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam("id") int id){
        ProdottoInCarrello  risultato=prodottoInCarrelloService.getById(id);
        if(risultato==null){
            return new ResponseEntity(new ResponseMessage("Nessun riscontro per questo id"), HttpStatus.OK);
        }
        return new ResponseEntity(risultato, HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") int id){prodottoInCarrelloService.removeProdotto(id);}

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid ProdottoInCarrello prodottoInCarrello){
        try {
            prodottoInCarrelloService.addProdotto(prodottoInCarrello);
        }catch(IllegalProductQuantityException e){
            return new ResponseEntity(new ResponseMessage("La quantità del prodotto non è disponibile!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ResponseMessage("Prodotto aggiunto correttamente al carrello"), HttpStatus.OK);
    }


}
