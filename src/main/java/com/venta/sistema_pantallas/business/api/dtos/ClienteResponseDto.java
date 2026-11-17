package com.venta.sistema_pantallas.business.api.dtos;

import com.venta.sistema_pantallas.business.data.entities.enums.TipoCliente;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDto {

    private Integer id;
    private String razonSocial;
    private String dniRuc;
    private String telefono;
    private String direccion;
    private TipoCliente tipoCliente;
    private Boolean esCredito;
    private BigDecimal limiteCredito;
    private Boolean estado;
}