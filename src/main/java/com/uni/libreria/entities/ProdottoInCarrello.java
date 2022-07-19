package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

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
    private int quantita;

    @Basic
    @Column(name="totale", nullable = false)
    private float totale;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="prodotto", referencedColumnName = "id")
    private Prodotto prodotto;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="carrello", referencedColumnName = "id")
    @ToString.Exclude
    private Carrello carrello;

}
