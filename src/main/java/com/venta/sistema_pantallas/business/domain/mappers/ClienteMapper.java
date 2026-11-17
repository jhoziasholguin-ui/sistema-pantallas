package com.venta.sistema_pantallas.business.domain.mappers;

import com.venta.sistema_pantallas.business.api.dtos.ClienteRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ClienteResponseDto;
import com.venta.sistema_pantallas.business.data.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Cliente toEntity(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto toResponseDto(Cliente cliente);

    List<ClienteResponseDto> toResponseListDto(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntity(
            ClienteRequestDto clienteRequestDto,
            @MappingTarget Cliente cliente
    );
}
