package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="prodotto", schema="libreria")
public class Prodotto {

    public enum Disponibilita {IMMEDIATA, PROSSIME_USCITE , IN_PRENOTAZIONE}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", length = 1000)
    private String descrizione;

    @Basic
    @Column(name = "immagine", length = Integer.MAX_VALUE)
    @Lob
    @JsonIgnore
    @ToString.Exclude
    private byte[] immagine;

    @ManyToOne
    @JoinColumn(name = "reparto")
    private Reparto reparto;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToStringExclude
    private List<ProdottoInOrdine> prodottoInOrdine;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "libro", referencedColumnName = "id")
    @JsonIgnore
    private Libro libro;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="cd", referencedColumnName = "id")
    @JsonIgnore
    private Cd cd;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "film", referencedColumnName = "id")
    @JsonIgnore
    private Film film;

    @Basic
    @Column(name="prezzo", nullable = false)
    private float prezzo;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantita;

    @Basic
    @Column(name = "disponibilità", nullable = false)
    @Enumerated(EnumType.STRING)
    private Disponibilita disponibilita;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private List<ProdottoInCarrello> prodottoInCarrello;

}
