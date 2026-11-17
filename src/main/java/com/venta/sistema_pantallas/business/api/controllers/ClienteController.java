package com.venta.sistema_pantallas.business.api.controllers;

import com.venta.sistema_pantallas.business.api.dtos.ClienteRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ClienteResponseDto;
import com.venta.sistema_pantallas.business.domain.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<ClienteResponseDto> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarPorId(
            @PathVariable Integer id
    ) {
        ClienteResponseDto cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDto>> buscarPorRazonSocial(
            @RequestParam String razonSocial
    ) {
        List<ClienteResponseDto> clientes = clienteService.buscarPorRazonSocial(razonSocial);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> crear(
            @Valid @RequestBody ClienteRequestDto clienteRequestDto
    ) {
        ClienteResponseDto clienteResponseDto = clienteService.crear(clienteRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteRequestDto clienteRequestDto
    ) {
        ClienteResponseDto clienteResponseDto = clienteService.actualizar(id, clienteRequestDto);
        return ResponseEntity.ok(clienteResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id
    ) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
