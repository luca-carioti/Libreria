package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@Table(name="carrello")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="data_creazione", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_creazione;

    @OneToOne
    @Column(name = "cliente")
    private Cliente cliente;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantità;

    @OneToMany(mappedBy = "carrello")
    private Set<CdInCarrello> cd_in_carrelo;

    @OneToMany(mappedBy = "carrello")
    private Set<FilmInCarrello> film_in_carrello;

    @OneToMany(mappedBy = "carrello")
    private Set<LibroInCarrello> libri_in_carrello;









}
