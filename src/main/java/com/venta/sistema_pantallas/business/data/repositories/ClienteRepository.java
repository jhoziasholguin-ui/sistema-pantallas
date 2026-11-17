package com.venta.sistema_pantallas.business.data.repositories;

import com.venta.sistema_pantallas.business.data.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Búsqueda exacta ignorando mayúsculas/minúsculas
    Optional<Cliente> findByRazonSocialIgnoreCase(String razonSocial);

    // Búsqueda parcial tipo LIKE %razonSocial% (ideal para buscadores en pantalla)
    List<Cliente> findByRazonSocialContainingIgnoreCase(String razonSocial);

    // Solo clientes activos que coincidan con la búsqueda
    List<Cliente> findByRazonSocialContainingIgnoreCaseAndEstadoTrue(String razonSocial);

    // Útil para validar duplicados antes de registrar
    boolean existsByRazonSocialIgnoreCase(String razonSocial);

    boolean existsByDniRuc(String dniRuc);

    Optional<Cliente> findByDniRuc(String dniRuc);
}