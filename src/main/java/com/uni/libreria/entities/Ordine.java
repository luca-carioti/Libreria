package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="ordine")
public class Ordine {

    public enum Stato {INVIATO, IN_PREPARAZIONE, SPEDITO, CONSEGNATO}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name = "indirizzo", nullable = false, length = 50)
    private String indirizzo;

    @Basic
    @Column(name="telefono", nullable = false, length = 20)
    private String telefono;

    @Basic
    @Column(name="mail", nullable = false, length = 50)
    private String mail;

    @Basic
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @Basic
    @Column(name="stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "cliente",referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<ProdottoInOrdine> prodottoInOrdine;






}
