package com.venta.sistema_pantallas.business.domain.services;

import com.venta.sistema_pantallas.business.api.dtos.ClienteRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crear(ClienteRequestDto clienteRequestDto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Integer id);

    List<ClienteResponseDto> buscarPorRazonSocial(String razonSocial);

    ClienteResponseDto actualizar(Integer id, ClienteRequestDto clienteRequestDto);

    void eliminar(Integer id);
}
