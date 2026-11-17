
package com.venta.sistema_pantallas.business.api.controllers;

import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ProductoPantallaResponseDto;
import com.venta.sistema_pantallas.business.domain.services.ProductoPantallaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos-pantalla")
@RequiredArgsConstructor
public class ProductoPantallaController {

    private final ProductoPantallaService productoPantallaService;

    @GetMapping
    public ResponseEntity<List<ProductoPantallaResponseDto>> listar() {

        List<ProductoPantallaResponseDto> productos = productoPantallaService.listar();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoPantallaResponseDto> buscarPorId(
            @PathVariable Long id
    ) {

        ProductoPantallaResponseDto producto = productoPantallaService.buscarPorId(id);

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<ProductoPantallaResponseDto> crear(
            @Valid @RequestBody ProductoPantallaRequestDto request
    ) {

        ProductoPantallaResponseDto producto = productoPantallaService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoPantallaResponseDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoPantallaRequestDto request
    ) {

        ProductoPantallaResponseDto producto = productoPantallaService.actualizar(id, request);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        productoPantallaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
