package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name="ordine")
public class Ordine {

    private enum Stato {INVIATO, IN_PREPARAZIONE, SPEDITO, CONSEGNATO}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name = "indirizzo", nullable = false, length = 50)
    private String indirizzo;

    @Basic
    @Column(name="telefono", nullable = false, length = 20)
    private String telefono;

    @Basic
    @Column(name="mail", nullable = false, length = 50)
    private String mail;

    @Basic
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Basic
    @Column(name="stato", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Stato stato;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.MERGE)
    private List<ProdottoInOrdine> prodottoInOrdine;






}
