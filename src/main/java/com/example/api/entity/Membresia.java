package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "membresia")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_membresia_id", nullable = false)
    private TipoMembresia tipoMembresia;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "miembro_id", nullable = false)
    @JsonBackReference
    private Miembro miembro;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @PrePersist
    public void calcularFechas() {
        this.fechaInicio = LocalDate.now();
        this.fechaFin = this.fechaInicio.plusMonths(this.tipoMembresia.getMes());
    }
}