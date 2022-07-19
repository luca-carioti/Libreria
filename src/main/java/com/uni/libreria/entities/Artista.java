package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="artista")
public class Artista {
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
    @Column(name="immagine", nullable = true, length = Integer.MAX_VALUE)
    @Lob
    @JsonIgnore
    @ToString.Exclude
    private byte[] immagine;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @Basic
    @Column(name="data_nascita", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date data_nascita;

    @Basic
    @Column(name="città", nullable=true, length = 90)
    private String città;

    @ManyToMany(mappedBy = "artisti")
    @ToString.Exclude
    @JsonIgnore
    private Set<Cd> cd;

    //TUTTO VERIFICATO


}
