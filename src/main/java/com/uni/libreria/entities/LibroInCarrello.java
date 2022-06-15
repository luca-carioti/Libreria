package com.uni.libreria.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="libro_in_carrello")
public class LibroInCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="quantità")
    private int quantità;

    @ManyToOne
    @JoinColumn(name="carrello")
    @ToString.Exclude
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name="libro")
    private Libro libro;
}
