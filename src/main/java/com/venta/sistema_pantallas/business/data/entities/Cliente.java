package com.venta.sistema_pantallas.business.data.entities;

import com.venta.sistema_pantallas.business.data.entities.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 250, unique = true)
    private String razonSocial;

    @Column(name = "dni_ruc", length = 11, nullable = true, unique = true)
    private String dniRuc;

    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoCliente tipoCliente;

    @Builder.Default
    @Column(name = "es_credito", nullable = false)
    private Boolean esCredito = false;

    @Builder.Default
    @Column(name = "limite_credito", precision = 10, scale = 2, nullable = false)
    private BigDecimal limiteCredito = BigDecimal.ZERO;


    @Builder.Default
    @Column(name = "estado", nullable = false)
    private Boolean estado = true;
}
