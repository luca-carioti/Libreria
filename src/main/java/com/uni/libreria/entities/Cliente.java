package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="cognome", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Column(name="data_nascita", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_nascita;

    @Basic
    @Column(name="mail", nullable=false, length = 50)
    private String mail;

    @Basic
    @Column(name="telefono", nullable=false, length = 20)
    private String telefono;

    @Basic
    @Column(name="indirizzo", nullable=false, length = 50)
    private String indirizzo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Ordine> ordini;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    private Carrello carrello;
}
