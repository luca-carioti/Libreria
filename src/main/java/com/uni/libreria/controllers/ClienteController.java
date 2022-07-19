package com.uni.libreria.controllers;

import ch.qos.logback.core.net.server.Client;
import com.uni.libreria.entities.Cliente;
import com.uni.libreria.services.ClienteService;
import com.uni.libreria.support.exceptions.UserMailAlreadyExistException;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity registra(@RequestBody @Valid Cliente cliente){
        try{
            clienteService.registraCliente(cliente);
        }catch(UserMailAlreadyExistException e){
            return new ResponseEntity(new ResponseMessage("Un account è gia associato a questa mail!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ResponseMessage("Registrazione avvenuta con successo!"),HttpStatus.OK);
    }

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam("id") int id){
        Cliente cliente=clienteService.findById(id);
        if(cliente==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza con questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(cliente,HttpStatus.OK);
    }

    @GetMapping("/search/by_mail")
    public ResponseEntity getByMail(@RequestParam("mail") String mail){
        Cliente cliente=clienteService.findByMail(mail);
        if(cliente==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questa mail!"), HttpStatus.OK);
        }
        return new ResponseEntity(cliente,HttpStatus.OK);
    }

    @GetMapping("/search/by_nome_and_cognome")
    public ResponseEntity getByNomeAndCognome(@RequestParam("nome") String nome, @RequestParam("cognome") String cognome){
        List<Cliente> clienti=clienteService.findByNomeAndCognome(nome,cognome);
        if(clienti.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questa mail!"), HttpStatus.OK);
        }
        return new ResponseEntity(clienti,HttpStatus.OK);
    }



    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Cliente> clienti=clienteService.findAll(pageNumber,pageSize,sortBy);
        if(clienti.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questa mail!"), HttpStatus.OK);
        }
        return new ResponseEntity(clienti,HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") int id){
        clienteService.deleteCliente(id);
    }


}
