package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="cd")
public class Cd {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="editore", referencedColumnName = "id")
    private Editore editore;

    @Basic
    @Column(name="data_pubblicazione", nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataPubblicazione;

    @ManyToMany
    @JoinTable(
            name="artisti_in_cd",
            joinColumns = @JoinColumn(name = "cd"),
            inverseJoinColumns = @JoinColumn(name="artista"))
    @JsonIgnore
    @ToString.Exclude
    private List<Artista> artisti;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "prodotto", referencedColumnName = "id")
    private Prodotto prodotto;

}
