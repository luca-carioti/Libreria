package com.uni.libreria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @ManyToOne
    @JoinColumn(name="editore")
    private Editore editore;

    @ManyToMany
    @JoinTable(
            name = "autori_in_libro",
            joinColumns = @JoinColumn(name = "libro"),
            inverseJoinColumns = @JoinColumn(name="autore"))
    @JsonIgnore
    private List<Autore> autori;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "prodotto", referencedColumnName = "id")
    private Prodotto prodotto;

}
