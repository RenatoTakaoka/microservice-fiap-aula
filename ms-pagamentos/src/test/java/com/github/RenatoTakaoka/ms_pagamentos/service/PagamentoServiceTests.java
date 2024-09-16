package com.github.RenatoTakaoka.ms_pagamentos.service;

import com.github.RenatoTakaoka.ms_pagamentos.dto.PagamentoDTO;
import com.github.RenatoTakaoka.ms_pagamentos.model.Pagamento;
import com.github.RenatoTakaoka.ms_pagamentos.repository.PagamentoRepository;
import com.github.RenatoTakaoka.ms_pagamentos.service.exception.ResourceNotFoundException;
import com.github.RenatoTakaoka.ms_pagamentos.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTests {

    @InjectMocks
    private PagamentoService service;

    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Pagamento pagamento;
    private PagamentoDTO pagamentoDTO;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 10L;

        Mockito.when(repository.existsById(existingId)).thenReturn(true);

        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

        Mockito.doNothing().when(repository).deleteById(existingId);

        pagamento = Factory.createPagamento();
        pagamentoDTO = new PagamentoDTO(pagamento);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(pagamento));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save(any())).thenReturn(pagamento);

        Mockito.when(repository.getReferenceById(existingId)).thenReturn(pagamento);

        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

    }


    @Test
    public void findByIdShouldReturnPagamentoDTOWhenIdExists() {
        PagamentoDTO result = service.findById(existingId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getValor(), pagamento.getValor());
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void insertShouldReturnPagamentoDTO() {
        PagamentoDTO result = service.insert(pagamentoDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), pagamento.getId());
    }

    @Test
    public void updateShouldReturnPagamentoDTOWhenIdExists() {
        PagamentoDTO result = service.update(pagamento.getId(), pagamentoDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getValor(), pagamento.getValor());
    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, pagamentoDTO);
        });
    }

    @Test
    @DisplayName("Delete deveria não fazer nda quando ID existe")
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.delete(nonExistingId);
        });
    }

}
