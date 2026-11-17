package com.venta.sistema_pantallas.business.api.dtos;
import com.venta.sistema_pantallas.business.data.entities.CalidadPantalla;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductoPantallaRequestDto {
    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 30, message = "El SKU no debe superar los 30 caracteres")
    private String sku;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 60, message = "La marca no debe superar los 60 caracteres")
    private String marca;

    @NotBlank(message = "El nombre base es obligatorio")
    @Size(max = 120, message = "El nombre base no debe superar los 120 caracteres")
    private String nombreBase;

    @NotNull(message = "La calidad de la pantalla es obligatoria")
    private CalidadPantalla calidad;

    private boolean tieneMarco;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    @Digits(integer = 8, fraction = 2, message = "El precio admite hasta 8 dígitos enteros y 2 decimales")
    private BigDecimal precioVenta;


}
