package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="prodotto")
public class Prodotto {

    private enum Disponibilità{IMMEDIATA, PROSSIME_USCITE, IN_PRENOTAZIONE}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @Basic
    @Column(name = "immagine", nullable = true, length = Integer.MAX_VALUE)
    @Lob
    @JsonIgnore
    @ToString.Exclude
    private byte[] immagine;

    @ManyToOne
    @JoinColumn(name = "reparto")
    private Reparto reparto;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.MERGE)
    private List<ProdottoInOrdine> prodottoInOrdine;

    @OneToOne
    @Column(name = "libro", nullable = true)
    private Libro libro;

    @OneToOne
    @Column(name="cd", nullable = true)
    private Cd cd;

    @OneToOne
    @Column(name = "film", nullable = true)
    private Film film;

    @Basic
    @Column(name="prezzo", nullable = false)
    private float prezzo;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantità;

    @Basic
    @Column(name = "disponibilità", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Disponibilità disponibilità;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<ProdottoInCarrello> prodottoInCarrello;

    //TUTTO VERIFICATO

}
