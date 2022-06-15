package com.uni.libreria.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="film", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "editore", "data_pubblicazione"}))
public class Film {

    private enum Disponibilità {IMMEDIATA, PROSSIME_USCITE, IN_PRENOTAZIONE}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @Basic
    @Column(name="immagine", nullable = true, length = Integer.MAX_VALUE)
    private byte[] immagine;

    @Basic
    @Column(name="prezzo", nullable = false)
    private int prezzo;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantità;

    @Basic
    @Column(name = "disponibilità", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Disponibilità disponibilità;

    @ManyToOne
    @JoinColumn(name="reparto")
    private Reparto reparto;

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
    private Set<Attore> attori;

    @ManyToMany
    @JoinTable(
            name="registi_in_film",
            joinColumns = @JoinColumn(name="registi"),
            inverseJoinColumns = @JoinColumn(name = "film"))
    private Set<Regista> registi;

    @OneToMany(mappedBy = "film")
    private Set<FilmInAcquisto> film_in_acquisto;

    @OneToMany(mappedBy = "film")
    private Set<FilmInCarrello> film_in_carello;


}
