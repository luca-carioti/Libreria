package com.uni.libreria.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name="cd_in_acquisto")
public class CdInAcquisto {
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
    @JoinColumn(name="cd")
    private Cd cd;



}
