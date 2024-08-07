package com.github.rkkt.ms_pagamento.service;


import com.github.rkkt.ms_pagamento.dto.PagamentoDTO;
import com.github.rkkt.ms_pagamento.model.Pagamento;
import com.github.rkkt.ms_pagamento.repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAll() {
        List<Pagamento> list = repository.findAll();
        return list.stream().map(PagamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findById(long id) {
        Pagamento entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado")
        );

        return new PagamentoDTO(entity);
    }

}
