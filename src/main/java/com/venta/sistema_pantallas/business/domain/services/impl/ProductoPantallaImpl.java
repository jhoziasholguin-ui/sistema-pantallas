package com.venta.sistema_pantallas.business.domain.services.impl;

import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaResponseDto;
import com.venta.sistema_pantallas.business.data.repositories.ProductoPantallaRepository;
import com.venta.sistema_pantallas.business.domain.mappers.ProductoPantallaMapper;
import com.venta.sistema_pantallas.business.domain.services.ProductoPantallaService;
import com.venta.sistema_pantallas.business.data.entities.ProductoPantalla;
import org.springframework.stereotype.Service;
import com.venta.sistema_pantallas.business.api.exceptions.ProductoPantallaNoEncontradoException;
import com.venta.sistema_pantallas.business.api.exceptions.SkuDuplicadoException;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoPantallaImpl implements ProductoPantallaService {

    private final ProductoPantallaRepository productoPantallaRepository;
    private final ProductoPantallaMapper productoPantallaMapper;


    @Override
    @Transactional
    public ProductoPantallaResponseDto crear(ProductoPantallaRequestDto request) {

        // Verificar si el SKU ya existe
        boolean skuExiste = productoPantallaRepository.existsBySku(request.getSku());

        if (skuExiste) {
            throw new SkuDuplicadoException(request.getSku());
        }

        // Convertir el DTO en una entidad
        ProductoPantalla producto = productoPantallaMapper.toEntity(request);

        // Guardar el producto en la base de datos
        ProductoPantalla productoGuardado = productoPantallaRepository.save(producto);

        log.info("Producto registrado con ID {}", productoGuardado.getId());

        // Convertir la entidad guardada en DTO de respuesta
        return productoPantallaMapper.toResponseDto(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoPantallaResponseDto> listar() {

        List<ProductoPantalla> productos = productoPantallaRepository.findAll();

        return productoPantallaMapper.toResponseListDto(productos);
    }

    @Override
    @Transactional
    public ProductoPantallaResponseDto buscarPorId(Long id) {

        ProductoPantalla producto = productoPantallaRepository.findById(id)
                .orElseThrow(() -> new ProductoPantallaNoEncontradoException(id));

        return productoPantallaMapper.toResponseDto(producto);
    }

    @Override
    @Transactional
    public ProductoPantallaResponseDto actualizar(Long id, ProductoPantallaRequestDto request) {

        ProductoPantalla producto = productoPantallaRepository.findById(id)
                .orElseThrow(() -> new ProductoPantallaNoEncontradoException(id));

        if (!producto.getSku().equals(request.getSku())
                && productoPantallaRepository.existsBySku(request.getSku())) {

            throw new SkuDuplicadoException(request.getSku());
        }

        productoPantallaMapper.updateEntity(request, producto);

        ProductoPantalla productoActualizado = productoPantallaRepository.save(producto);

        log.info("Producto actualizado con ID {}", id);

        return productoPantallaMapper.toResponseDto(productoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        ProductoPantalla producto = productoPantallaRepository.findById(id)
                .orElseThrow(() -> new ProductoPantallaNoEncontradoException(id));

        productoPantallaRepository.delete(producto);

        log.info("Producto eliminado con ID {}", id);
    }
}
