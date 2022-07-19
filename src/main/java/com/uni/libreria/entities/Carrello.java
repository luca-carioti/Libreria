package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCreazione;

    @OneToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<ProdottoInCarrello> prodottoInCarrello;

    @Basic
    @Column(name="totale", nullable = false)
    private int totale;

}
