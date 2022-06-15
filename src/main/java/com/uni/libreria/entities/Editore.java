package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="editore")
public class Editore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="immagine", nullable = false, length = Integer.MAX_VALUE)
    private byte[] immagine;

    @Basic
    @Column(name="descrizione", nullable = false, length = 1000)
    private String descrizione;

    @OneToMany(mappedBy = "editore")
    private Set<Libro> libri;

    @OneToMany(mappedBy = "editore")
    private Set<Film> film;

    @OneToMany(mappedBy = "editore")
    private Set<Cd> cd;

}
