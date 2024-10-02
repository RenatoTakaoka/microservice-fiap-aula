package com.github.RenatoTakaoka.ms_pagamentos.service;


import com.github.RenatoTakaoka.ms_pagamentos.http.PedidoClient;
import com.github.RenatoTakaoka.ms_pagamentos.model.Pagamento;
import com.github.RenatoTakaoka.ms_pagamentos.model.Status;
import com.github.RenatoTakaoka.ms_pagamentos.repository.PagamentoRepository;
import com.github.RenatoTakaoka.ms_pagamentos.service.exception.DatabaseException;
import com.github.RenatoTakaoka.ms_pagamentos.service.exception.ResourceNotFoundException;
import com.github.RenatoTakaoka.ms_pagamentos.dto.PagamentoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PedidoClient pedidoClient;

    @Autowired
    private PagamentoRepository repository;

//    @Transactional(readOnly = true)
//    public Page<PagamentoDTO> findAll(Pageable pageable) {
//        Page<Pagamento> page = repository.findAll(pageable);
//        return page.map(PagamentoDTO::new);
//    }

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAll() {
        return repository.findAll().stream()
                .map(PagamentoDTO::new).collect(Collectors.toList());
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
            entity = repository.save(entity);
            return new PagamentoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
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

    @Transactional
    public void confirmarPagamentoDePedido(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);

        if (pagamento.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado com id: " + id);
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedidoClient.atualizarPagamentoDoPedido(pagamento.get().getPedidoId());
    }

    private void copyDtoToEntity(PagamentoDTO dto, Pagamento entity) {
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setValidade(dto.getValidade());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSeguranca());
        entity.setStatus(Status.CRIADO);
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamentoId());
    }

    @Transactional
    public void alterarStatusDoPagamento(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);

        if(pagamento.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
        }

        pagamento.get().setStatus(Status.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento.get());
    }

}
