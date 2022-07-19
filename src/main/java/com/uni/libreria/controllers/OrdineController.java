package com.uni.libreria.controllers;

import com.uni.libreria.entities.Cliente;
import com.uni.libreria.entities.Ordine;
import com.uni.libreria.services.OrdineService;
import com.uni.libreria.support.exceptions.ClienteNotFoundException;
import com.uni.libreria.support.exceptions.IllegalProductQuantityException;
import com.uni.libreria.support.other.ResponseMessage;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid Ordine ordine){
        try{
            ordineService.addOrdine(ordine);
        }catch (IllegalProductQuantityException e){
            return new ResponseEntity(new ResponseMessage("Un prdotto non è più disponibile!"), HttpStatus.OK);
        }
        return new ResponseEntity(new ResponseMessage("Il tuo ordine è andato a buon fine!"), HttpStatus.OK);
    }

    @GetMapping("/search/by_cliente")
    public ResponseEntity getByCliente(@RequestParam("cliente") Cliente cliente,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=new ArrayList<>();
       try{
           ordini=ordineService.findByCliente(cliente,pageNumber,pageSize,sortBy);
       }catch(ClienteNotFoundException e){
           return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo cliente!"), HttpStatus.BAD_REQUEST);
       }
       if(ordini.size()<=0){
           return new ResponseEntity(new ResponseMessage("Nessun ordine per questo cliente"), HttpStatus.OK);
       }
       return new ResponseEntity(ordini, HttpStatus.OK);
    }

    @GetMapping("/search/by_data")
    public ResponseEntity getByData(@RequestParam("data")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date data,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=new ArrayList<>();
        try{
            ordini=ordineService.findByData(data,pageNumber,pageSize,sortBy);
        }catch(ClienteNotFoundException e){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo cliente!"), HttpStatus.BAD_REQUEST);
        }
        if(ordini.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun ordine per questa data"), HttpStatus.OK);
        }
        return new ResponseEntity(ordini, HttpStatus.OK);
    }

    @GetMapping("/search/by_stato")
    public ResponseEntity getByStato(@RequestParam("stato") Ordine.Stato stato,
                                     @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=ordineService.findByStato(stato,pageNumber,pageSize,sortBy);
        if(ordini.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo stato!"), HttpStatus.OK);
        }
        return new ResponseEntity(ordini,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(
                                     @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=ordineService.findAll(pageNumber,pageSize,sortBy);
        if(ordini.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza!"), HttpStatus.OK);
        }
        return new ResponseEntity(ordini,HttpStatus.OK);
    }

    @GetMapping("/search/by_mail")
    public ResponseEntity getByMail(@RequestParam("mail") String mail,
                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=new ArrayList<>();
        try{
            ordini=ordineService.findByMail(mail,pageNumber,pageSize,sortBy);
        }catch(ClienteNotFoundException e){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo cliente!"), HttpStatus.BAD_REQUEST);
        }
        if(ordini.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun ordine per questo cliente"), HttpStatus.OK);
        }
        return new ResponseEntity(ordini, HttpStatus.OK);
    }

    @GetMapping("/search/advanced")
    public ResponseEntity advanced(@RequestParam(value = "cliente", required = false) Cliente cliente,
                                   @RequestParam(value = "data", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data,
                                   @RequestParam(value = "stato", required = false) Ordine.Stato stato,
                                   @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                   @RequestParam(value = "sortBy", defaultValue = "data") String sortBy){
        List<Ordine> ordini=ordineService.advancedSearch(cliente,data,stato,pageNumber,pageSize,sortBy);
        if(ordini.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(ordini,HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){ordineService.removeOrdine(id);}



}
