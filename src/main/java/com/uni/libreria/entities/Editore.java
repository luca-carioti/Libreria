package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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
    @Column(name="immagine", nullable = true, length = Integer.MAX_VALUE)
    @Lob
    @JsonIgnore
    @ToString.Exclude
    private byte[] immagine;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @OneToMany(mappedBy = "editore")
    @JsonIgnore
    @ToString.Exclude
    private List<Libro> libri;

    @OneToMany(mappedBy = "editore")
    @JsonIgnore
    @ToString.Exclude
    private Set<Film> film;

    @OneToMany(mappedBy = "editore", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private Set<Cd> cd;

}
