package com.uni.libreria.controllers;

import ch.qos.logback.core.net.server.Client;
import com.uni.libreria.entities.Carrello;
import com.uni.libreria.entities.Cliente;
import com.uni.libreria.services.CarrelloService;
import com.uni.libreria.support.exceptions.CartAlreadyExistException;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import com.uni.libreria.support.other.ResponseMessage;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/carrelli")
public class CarrelloController {
    @Autowired
    private CarrelloService carrelloService;

    @GetMapping("/search/by_cliente")
    public ResponseEntity getByCliente(@RequestParam("cliente") Cliente cliente){
        Carrello carrello=carrelloService.findByCliente(cliente);
        if(carrello==null){
            return new ResponseEntity(new ResponseMessage("Nessun carrello trovato per questo cliente!"), HttpStatus.OK);
        }
        return new ResponseEntity(carrello,HttpStatus.OK);
    }

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam("id") int id){
        Carrello carrello=carrelloService.findById(id);
        if(carrello==null){
            return new ResponseEntity(new ResponseMessage("Nessun carrello trovato per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(carrello,HttpStatus.OK);
    }


    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "dataCreazione") String sortBy){
        List<Carrello> carrelli=carrelloService.findAll(pageNumber,pageSize,sortBy);
        if(carrelli.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun carrello presente!"), HttpStatus.OK);
        }
        return new ResponseEntity(carrelli, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") int id){
        carrelloService.removeCarrello(id);
        return new ResponseEntity(new ResponseMessage("Carrello rimosso con successo!"), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid Carrello carrello){
        try{
            carrelloService.addCarrello(carrello);
        }catch(CartAlreadyExistException e){
            return new ResponseEntity(new ResponseMessage("Il carrello esiste!"),HttpStatus.BAD_REQUEST);
        }catch(IllegalProductQuantityException e){
            return new ResponseEntity(new ResponseMessage("Qualche prodotto non è più disponiibile!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ResponseMessage("Il carrello è stato creato correttamente!"), HttpStatus.OK);
    }




}
