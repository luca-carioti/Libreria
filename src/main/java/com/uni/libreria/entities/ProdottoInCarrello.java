package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "prodotto_in_carrello")
public class ProdottoInCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantità;

    @Basic
    @Column(name="totale", nullable = false)
    private float totale;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="prodotto")
    private Prodotto prodotto;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="carrello")
    private Carrello carrello;

}
