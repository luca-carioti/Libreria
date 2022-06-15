package com.uni.libreria.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="film_in_carrello")
public class FilmInCarrello {
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
    @JoinColumn(name="film")
    private Film film;
}
