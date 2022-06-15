package com.uni.libreria.entities;

import lombok.Data;


import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name="reparto")
public class Reparto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @OneToMany(mappedBy="reparto")
    private Set<Libro> libri;

    @OneToMany(mappedBy = "reparto")
    private Set<Film> film;

    @OneToMany(mappedBy = "reparto")
    private Set<Cd> cd;




}
