package com.venta.sistema_pantallas.business.api.dtos;

import com.venta.sistema_pantallas.business.data.entities.enums.TipoCliente;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDto {

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 250, message = "La razón social no puede superar los 250 caracteres")
    private String razonSocial;

    @Size(min = 8, max = 11, message = "El DNI o RUC debe tener entre 8 y 11 dígitos")
    @Pattern(regexp = "^[0-9]*$", message = "El DNI o RUC solo debe contener números")
    private String dniRuc;

    @Size(max = 9, message = "El teléfono no puede superar los 9 dígitos")
    @Pattern(regexp = "^9[0-9]{8}$", message = "El teléfono solo debe contener 9 números y empezar con 9")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    private String direccion;

    @NotNull(message = "El tipo de cliente es obligatorio")
    private TipoCliente tipoCliente;

    @Builder.Default
    private Boolean esCredito = false;

    @NotNull(message = "El límite de crédito es obligatorio")
    @DecimalMin(value = "0.00", message = "El límite de crédito no puede ser negativo")
    @Digits(integer = 8, fraction = 2, message = "Formato de límite de crédito inválido.")
    @Builder.Default
    private BigDecimal limiteCredito = BigDecimal.ZERO;
}