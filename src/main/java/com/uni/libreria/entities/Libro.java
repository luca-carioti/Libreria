package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="data_pubblicazione", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_pubblicazione;

    @ManyToOne
    @JoinColumn(name="editore")
    private Editore editore;

    @ManyToMany
    @JoinTable(
            name = "autori_in_libro",
            joinColumns = @JoinColumn(name = "libro"),
            inverseJoinColumns = @JoinColumn(name="autore"))
    private List<Autore> autori;

    @OneToOne(mappedBy = "libro")
    private Prodotto prodotto;

    //TUTTO VERIFICATO
}
