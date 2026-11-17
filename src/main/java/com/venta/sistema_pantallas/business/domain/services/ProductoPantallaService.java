package com.venta.sistema_pantallas.business.domain.services;

import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaResponseDto;
import java.util.List;

public interface ProductoPantallaService {

    ProductoPantallaResponseDto crear(ProductoPantallaRequestDto request);

    List<ProductoPantallaResponseDto> listar();

    ProductoPantallaResponseDto buscarPorId(Long id);

    ProductoPantallaResponseDto actualizar(Long id, ProductoPantallaRequestDto request);

    void eliminar(Long id);
}
