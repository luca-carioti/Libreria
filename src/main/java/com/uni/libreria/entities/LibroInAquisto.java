package com.uni.libreria.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name="libri_in_acquisto")
public class LibroInAquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="quantità")
    private int quantità;

    @ManyToOne
    @JoinColumn(name="acquisto")
    @ToString.Exclude
    private Acquisto acquisto;

    @ManyToOne
    @JoinColumn(name="libro")
    private Libro libro;
}
