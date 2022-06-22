package com.uni.libreria.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="carrello")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="data_creazione", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_creazione;

    @OneToOne (mappedBy = "carrello")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.MERGE)
    private List<ProdottoInCarrello> prodottoInCarrello;

    @Basic
    @Column(name="totale", nullable = false)
    private int totale;

    //TUTTO VERIFICATO
}
