package com.venta.sistema_pantallas.business.domain.mappers;

import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaResponseDto;
import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaRequestDto;
import com.venta.sistema_pantallas.business.data.entities.ProductoPantalla;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoPantallaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stockDisponible", constant = "0")
    @Mapping(target = "stockCondicion", constant = "0")
    ProductoPantalla toEntity(ProductoPantallaRequestDto request);

    ProductoPantallaResponseDto toResponseDto(ProductoPantalla producto);

    List<ProductoPantallaResponseDto> toResponseListDto(
            List<ProductoPantalla> productos
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stockDisponible", ignore = true)
    @Mapping(target = "stockCondicion", ignore = true)
    void updateEntity(
            ProductoPantallaRequestDto request,
            @MappingTarget ProductoPantalla producto
    );
}
