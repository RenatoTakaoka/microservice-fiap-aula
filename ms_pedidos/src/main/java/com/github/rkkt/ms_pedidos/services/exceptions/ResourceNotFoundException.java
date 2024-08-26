package com.github.rkkt.ms_pedidos.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Recurso não encontrado com id: " + id);
    }

}
