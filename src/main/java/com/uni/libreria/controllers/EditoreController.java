package com.uni.libreria.controllers;

import com.uni.libreria.entities.Cd;
import com.uni.libreria.entities.Editore;
import com.uni.libreria.services.EditoreService;
import com.uni.libreria.support.exceptions.EditoreAlreadyExistException;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/editori")
public class EditoreController {
    @Autowired
    private EditoreService editoreService;

    @GetMapping("/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Editore ris=editoreService.findById(id);
        if(ris==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(name="pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy){
        List<Editore> editori=editoreService.findAll(pageNumber,pageSize,sortBy);
        if(editori.size()<=0){
            return new ResponseEntity(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity(editori, HttpStatus.OK);
    }

    @GetMapping("/search/by_nome")
    public ResponseEntity getByNome(@RequestParam(name = "nome") String nome){
        Editore ris= editoreService.findByNome(nome);
        if(ris==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corrispondenza per questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(ris,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid Editore editore){
        try{
            editoreService.addEditore(editore);
        }catch(EditoreAlreadyExistException e){
            return new ResponseEntity(new ResponseMessage("L'editore esiste già!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ResponseMessage("Editore aggiunto!"),HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@RequestParam(name = "id") int id){editoreService.removeEditore(id);}
}
