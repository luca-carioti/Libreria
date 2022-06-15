package com.uni.libreria.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="cd", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "editore", "data_pubblicazione"}))
public class Cd {
    private enum Disponibilità {IMMEDIATA, PROSSIME_USCITE, IN_PRENOTAZIONE}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable = false, length = 1000)
    private String descrizione;

    @Basic
    @Column(name="immagine", nullable = true, length = Integer.MAX_VALUE)
    private Byte[] immagine;

    @Basic
    @Column(name="prezzo", nullable = false)
    private int prezzo;

    @Basic
    @Column(name="quantità", nullable = false)
    private int quantità;

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

    @Basic
    @Column(name="data_pubblicazione")
    @Temporal(TemporalType.DATE)
    private Date data_pubblicazione;

    @ManyToMany
    @JoinTable(
            name="artisti_in_cd",
            joinColumns = @JoinColumn(name = "cd"),
            inverseJoinColumns = @JoinColumn(name="artista"))
    private Set<Artista> artisti;

    @OneToMany(mappedBy = "cd")
    private Set<CdInAcquisto> cd_in_acquisto;

    @OneToMany(mappedBy = "cd")
    private Set<CdInCarrello> cd_in_carrello;


}
