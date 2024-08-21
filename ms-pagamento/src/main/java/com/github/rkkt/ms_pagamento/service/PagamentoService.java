package com.github.rkkt.ms_pagamento.service;


import com.github.rkkt.ms_pagamento.dto.PagamentoDTO;
import com.github.rkkt.ms_pagamento.model.Pagamento;
import com.github.rkkt.ms_pagamento.model.Status;
import com.github.rkkt.ms_pagamento.repository.PagamentoRepository;

import com.github.rkkt.ms_pagamento.service.exception.DatabaseException;
import com.github.rkkt.ms_pagamento.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public Page<PagamentoDTO> findAll(Pageable pageable) {
        Page<Pagamento> page = repository.findAll(pageable);
        return page.map(PagamentoDTO::new);
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findById(Long id) {
        Pagamento entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto) {
        Pagamento entity = new Pagamento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        try {
            Pagamento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new PagamentoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(PagamentoDTO dto, Pagamento entity) {
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setValidade(dto.getValidade());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSeguranca());
        entity.setStatus(Status.CRIADO);
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamento());
    }

}
