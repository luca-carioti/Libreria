package com.uni.libreria.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="acquisto")
public class Acquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "acquisto")
    private Set<LibroInAquisto> libri;

    @OneToMany(mappedBy = "acquisto")
    private Set<CdInAcquisto> cd;

    @OneToMany(mappedBy = "acquisto")
    private Set<FilmInAcquisto> film;





}
