package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="libro", uniqueConstraints = @UniqueConstraint(columnNames = {"nome","editore","data_pubblicazione"}))
public class Libro {
    private enum Disponibilità {IMMEDIATA, PROSSIME_USCITE, IN_PRENOTAZIONE};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable=true, length = 1000)
    private String descrizione;

    @Basic
    @Column(name="immagine", nullable = true, length = Integer.MAX_VALUE)
    private byte[] immagine;

    @Basic
    @Column(name="prezzo", nullable=false)
    private int prezzo;

    @Basic
    @Column(name = "quantità", nullable = false)
    private int quantità;

    @Basic
    @Column(name="data_pubblicazione", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_pubblicazione;

    @Basic
    @Column(name="disponibilità", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Disponibilità disponibilità;


    @ManyToOne
    @JoinColumn(name="reparto")
    private Reparto reparto;

    @ManyToOne
    @JoinColumn(name="editore")
    private Editore editore;

    @ManyToMany
    @JoinTable(
            name = "autori_in_libro",
            joinColumns = @JoinColumn(name = "libro"),
            inverseJoinColumns = @JoinColumn(name="autore"))
    private Set<Autore> autori;

    @OneToMany(mappedBy = "libro")
    private Set<LibroInAquisto> libro_in_acquisto;

    @OneToMany(mappedBy = "libro")
    private Set<LibroInCarrello> libro_in_carrello;



}
