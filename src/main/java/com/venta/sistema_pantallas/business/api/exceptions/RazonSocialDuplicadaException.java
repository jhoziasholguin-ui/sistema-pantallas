package com.venta.sistema_pantallas.business.api.exceptions;

public class RazonSocialDuplicadaException extends RuntimeException {

    public RazonSocialDuplicadaException(String razonSocial) {
        super("Ya existe un cliente registrado con la razón social: " + razonSocial);
    }
}

