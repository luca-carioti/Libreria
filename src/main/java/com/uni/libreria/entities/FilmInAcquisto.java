package com.uni.libreria.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name="film_in_acquisto")
public class FilmInAcquisto {
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
    @JoinColumn(name="film")
    private Film film;
}
