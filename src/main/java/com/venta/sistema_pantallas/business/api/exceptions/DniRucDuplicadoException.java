package com.venta.sistema_pantallas.business.api.exceptions;

public class DniRucDuplicadoException extends RuntimeException {

    public DniRucDuplicadoException(String dniRuc) {
        super("Ya existe un cliente registrado con el DNI/RUC: " + dniRuc);
    }
}