package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="editore")
    private Editore editore;

    @Basic
    @Column(name="data_pubblicazione", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_pubblicazione;

    @ManyToMany
    @JoinTable(
            name="attori_in_film",
            joinColumns = @JoinColumn(name = "film"),
            inverseJoinColumns = @JoinColumn(name = "attore"))
    @JsonIgnore
    private List<Attore> attori;

    @ManyToMany
    @JoinTable(
            name="registi_in_film",
            joinColumns = @JoinColumn(name="film"),
            inverseJoinColumns = @JoinColumn(name = "regista"))
    @JsonIgnore
    private List<Regista> registi;

    @OneToOne(mappedBy = "film")
    private Prodotto prodotto;

    //TUTTO VERIFICATO




}
