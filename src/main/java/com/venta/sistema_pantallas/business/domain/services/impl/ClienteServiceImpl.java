package com.venta.sistema_pantallas.business.domain.services.impl;

import com.venta.sistema_pantallas.business.api.dtos.ClienteRequestDto;
import com.venta.sistema_pantallas.business.api.dtos.ClienteResponseDto;
import com.venta.sistema_pantallas.business.api.exceptions.ClienteNoEncontradoException;
import com.venta.sistema_pantallas.business.api.exceptions.DniRucDuplicadoException;
import com.venta.sistema_pantallas.business.api.exceptions.RazonSocialDuplicadaException;
import com.venta.sistema_pantallas.business.data.entities.Cliente;
import com.venta.sistema_pantallas.business.data.repositories.ClienteRepository;
import com.venta.sistema_pantallas.business.domain.mappers.ClienteMapper;
import com.venta.sistema_pantallas.business.domain.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional
    public ClienteResponseDto crear(ClienteRequestDto clienteRequestDto) {

        if (clienteRepository.existsByRazonSocialIgnoreCase(clienteRequestDto.getRazonSocial())) {
            throw new RazonSocialDuplicadaException(clienteRequestDto.getRazonSocial());
        }

        if (clienteRequestDto.getDniRuc() != null && !clienteRequestDto.getDniRuc().isBlank()) {
            if (clienteRepository.existsByDniRuc(clienteRequestDto.getDniRuc())) {
                throw new DniRucDuplicadoException(clienteRequestDto.getDniRuc());
            }
        }

        Cliente cliente = clienteMapper.toEntity(clienteRequestDto);

        // Asegurar valores por defecto si vienen nulos en el JSON
        if (cliente.getEsCredito() == null) {
            cliente.setEsCredito(false);
        }
        if (cliente.getLimiteCredito() == null) {
            cliente.setLimiteCredito(BigDecimal.ZERO);
        }

        Cliente clienteGuardado = clienteRepository.save(cliente);

        log.info("Cliente registrado con ID {}", clienteGuardado.getId());

        return clienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDto buscarPorId(Integer id) {

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

        if (clienteEncontrado.isEmpty()) {
            throw new ClienteNoEncontradoException(id);
        }

        return clienteMapper.toResponseDto(clienteEncontrado.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toResponseListDto(clientes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDto> buscarPorRazonSocial(String razonSocial) {
        List<Cliente> clientes = clienteRepository.findByRazonSocialContainingIgnoreCase(razonSocial);
        return clienteMapper.toResponseListDto(clientes);
    }

    @Override
    @Transactional
    public ClienteResponseDto actualizar(Integer id, ClienteRequestDto clienteRequestDto) {

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

        if (clienteEncontrado.isEmpty()) {
            throw new ClienteNoEncontradoException(id);
        }

        Cliente cliente = clienteEncontrado.get();

        Optional<Cliente> clienteConRazonSocial = clienteRepository.findByRazonSocialIgnoreCase(clienteRequestDto.getRazonSocial());

        if (clienteConRazonSocial.isPresent() && !clienteConRazonSocial.get().getId().equals(id)) {
            throw new RazonSocialDuplicadaException(clienteRequestDto.getRazonSocial());
        }

        if (clienteRequestDto.getDniRuc() != null && !clienteRequestDto.getDniRuc().isBlank()) {
            Optional<Cliente> clienteConDniRuc = clienteRepository.findByDniRuc(clienteRequestDto.getDniRuc());

            if (clienteConDniRuc.isPresent() && !clienteConDniRuc.get().getId().equals(id)) {
                throw new DniRucDuplicadoException(clienteRequestDto.getDniRuc());
            }
        }

        clienteMapper.updateEntity(clienteRequestDto, cliente);

        if (cliente.getEsCredito() == null) {
            cliente.setEsCredito(false);
        }
        if (cliente.getLimiteCredito() == null) {
            cliente.setLimiteCredito(BigDecimal.ZERO);
        }

        Cliente clienteActualizado = clienteRepository.save(cliente);

        log.info("Cliente actualizado con ID {}", id);

        return clienteMapper.toResponseDto(clienteActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

        if (clienteEncontrado.isEmpty()) {
            throw new ClienteNoEncontradoException(id);
        }

        clienteRepository.delete(clienteEncontrado.get());

        log.info("Cliente eliminado con ID {}", id);
    }
}
