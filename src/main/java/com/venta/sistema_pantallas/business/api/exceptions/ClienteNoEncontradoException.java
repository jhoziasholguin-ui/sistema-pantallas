package com.venta.sistema_pantallas.business.api.exceptions;

public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(Integer id) {
        super("No se encontró el cliente con ID: " + id);
    }

    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}