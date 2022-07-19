package com.uni.libreria.controllers;

import com.uni.libreria.entities.Artista;
import com.uni.libreria.entities.Regista;
import com.uni.libreria.services.ArtistaService;
import com.uni.libreria.services.RegistaService;
import com.uni.libreria.support.other.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/registi")
public class RegistaController {
    @Autowired
    private RegistaService registaService;

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public void add(@RequestBody @Valid Regista regista){registaService.addRegista(regista);}

    @GetMapping("/search/all")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy){
        List<Regista> registi= registaService.findAll(pageNumber,pageSize,sortBy);
        if(registi.size()<=0){
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato trovato!"), HttpStatus.OK);
        }
        return new ResponseEntity(registi, HttpStatus.OK);
    }

    @GetMapping( "/search/by_id")
    public ResponseEntity getById(@RequestParam(name = "id") int id){
        Regista regista = registaService.findById(id);
        if(regista==null){
            return new ResponseEntity(new ResponseMessage("Nessuna corispondenza con questo id!"), HttpStatus.OK);
        }
        return new ResponseEntity(regista, HttpStatus.OK);
    }

    @GetMapping("/search/by_nome_and_cognome")
    public ResponseEntity getByNomeAndCognome (@RequestParam(name = "nome" , defaultValue = "") String nome , @RequestParam(name = "cognome", defaultValue = "") String cognome){
        List<Regista> registi= registaService.findByNomeAndCognome(nome,cognome);
        if(registi.size()<=0){
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(registi, HttpStatus.OK);
    }

    @DeleteMapping
    public void delete (@RequestParam(name = "id") int id){registaService.removeRegista(id);}
}
