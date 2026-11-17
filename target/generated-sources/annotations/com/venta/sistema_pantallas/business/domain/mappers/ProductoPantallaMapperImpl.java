package com.venta.sistema_pantallas.business.domain.mappers;

import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaResponseDto;
import com.venta.sistema_pantallas.business.data.entities.ProductoPantalla;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-17T07:46:47-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5 (Eclipse Adoptium)"
)
@Component
public class ProductoPantallaMapperImpl implements ProductoPantallaMapper {

    @Override
    public ProductoPantalla toEntity(ProductoPantallaRequestDto request) {
        if ( request == null ) {
            return null;
        }

        ProductoPantalla.ProductoPantallaBuilder productoPantalla = ProductoPantalla.builder();

        productoPantalla.sku( request.getSku() );
        productoPantalla.marca( request.getMarca() );
        productoPantalla.nombreBase( request.getNombreBase() );
        productoPantalla.calidad( request.getCalidad() );
        productoPantalla.tieneMarco( request.isTieneMarco() );
        productoPantalla.precioVenta( request.getPrecioVenta() );

        productoPantalla.stockDisponible( 0 );
        productoPantalla.stockCondicion( 0 );

        return productoPantalla.build();
    }

    @Override
    public ProductoPantallaResponseDto toResponseDto(ProductoPantalla producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoPantallaResponseDto.ProductoPantallaResponseDtoBuilder productoPantallaResponseDto = ProductoPantallaResponseDto.builder();

        productoPantallaResponseDto.id( producto.getId() );
        productoPantallaResponseDto.sku( producto.getSku() );
        productoPantallaResponseDto.marca( producto.getMarca() );
        productoPantallaResponseDto.nombreBase( producto.getNombreBase() );
        productoPantallaResponseDto.calidad( producto.getCalidad() );
        productoPantallaResponseDto.tieneMarco( producto.isTieneMarco() );
        productoPantallaResponseDto.precioVenta( producto.getPrecioVenta() );
        productoPantallaResponseDto.stockDisponible( producto.getStockDisponible() );
        productoPantallaResponseDto.stockCondicion( producto.getStockCondicion() );

        return productoPantallaResponseDto.build();
    }

    @Override
    public List<ProductoPantallaResponseDto> toResponseListDto(List<ProductoPantalla> productos) {
        if ( productos == null ) {
            return null;
        }

        List<ProductoPantallaResponseDto> list = new ArrayList<ProductoPantallaResponseDto>( productos.size() );
        for ( ProductoPantalla productoPantalla : productos ) {
            list.add( toResponseDto( productoPantalla ) );
        }

        return list;
    }

    @Override
    public void updateEntity(ProductoPantallaRequestDto request, ProductoPantalla producto) {
        if ( request == null ) {
            return;
        }

        producto.setSku( request.getSku() );
        producto.setMarca( request.getMarca() );
        producto.setNombreBase( request.getNombreBase() );
        producto.setCalidad( request.getCalidad() );
        producto.setTieneMarco( request.isTieneMarco() );
        producto.setPrecioVenta( request.getPrecioVenta() );
    }
}
