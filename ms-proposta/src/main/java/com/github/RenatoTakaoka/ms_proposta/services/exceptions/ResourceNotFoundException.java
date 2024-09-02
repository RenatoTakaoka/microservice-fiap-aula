package com.github.RenatoTakaoka.ms_proposta.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Recurso não encontrado com id: " + id);
    }

}
