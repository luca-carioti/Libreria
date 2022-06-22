package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="cd")
public class Cd {
    private enum Disponibilità {IMMEDIATA, PROSSIME_USCITE, IN_PRENOTAZIONE}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="editore")
    private Editore editore;

    @Basic
    @Column(name="data_pubblicazione", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date data_pubblicazione;

    @ManyToMany
    @JoinTable(
            name="artisti_in_cd",
            joinColumns = @JoinColumn(name = "cd"),
            inverseJoinColumns = @JoinColumn(name="artista"))
    @JsonIgnore
    @ToString.Exclude
    private Set<Artista> artisti;

    @OneToOne(mappedBy = "cd")
    private Prodotto prodotto;

    //TUTTO VERIFICATO




}
