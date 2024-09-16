package com.github.RenatoTakaoka.ms_pagamentos.service;

import com.github.RenatoTakaoka.ms_pagamentos.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class PagamentoServiceIT {

    @Autowired
    private PagamentoService service;

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 50L;
        countTotalPagamento = 6L; // Possuem 6 registros no import.sql
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {
        service.delete(existingId);
        Assertions.assertEquals(countTotalPagamento - 1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(RuntimeException.class, () -> {
           service.delete(nonExistingId);
        });
    }

    @Test
    public void findAllShouldReturnListPagamentoDTO() {
        var result = service. findAll();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalPagamento, result.size());
        Assertions.assertEquals(Double.valueOf(980.00), result.get(0).getValor().doubleValue());
        Assertions.assertEquals("Jesus da Silva", result.get(0).getNome());
        Assertions.assertEquals("José Malaquias", result.get(1).getNome());
        Assertions.assertNull(result.get(5).getNome());
    }

}
