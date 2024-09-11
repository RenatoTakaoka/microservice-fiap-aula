package com.github.RenatoTakaoka.ms_pagamento.repository;

import com.github.RenatoTakaoka.ms_pagamento.model.Pagamento;
import com.github.RenatoTakaoka.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1l;
        countTotalPagamento = 3L;
        nonExistingId = 10L;
    }

    @Test
    @DisplayName("findById deve retornar um optional não vazio quando ID existe")
    public void findByIdShouldReturnNonEmptyOptionalWhenExistsId() {
        Optional<Pagamento> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("findById deve retornar um optional vazio quando ID não existe")
    public void findByIdShouldReturnEmptyOptionalWhenIdDontExists() {
        Optional<Pagamento> result = repository.findById(nonExistingId);
        Assertions.assertFalse(result.isPresent());
    }


    @Test
    @DisplayName("Deveria excluir pagamento quando ID existe")
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Pagamento> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveShouldPersistWithoutIncrementWhenIdIsNull() {
        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        Assertions.assertEquals(countTotalPagamento + 1, pagamento.getId());
    }

}
