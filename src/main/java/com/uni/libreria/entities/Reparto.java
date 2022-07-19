package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringExclude;


import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="reparto")
public class Reparto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name="descrizione", nullable = true, length = 1000)
    private String descrizione;

    @OneToMany(mappedBy = "reparto", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToStringExclude
    private List<Prodotto> prodotti;




}
