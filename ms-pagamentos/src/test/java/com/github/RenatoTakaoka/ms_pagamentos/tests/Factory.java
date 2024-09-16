package com.github.RenatoTakaoka.ms_pagamentos.tests;

import com.github.RenatoTakaoka.ms_pagamentos.dto.PagamentoDTO;
import com.github.RenatoTakaoka.ms_pagamentos.model.Pagamento;
import com.github.RenatoTakaoka.ms_pagamentos.model.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento createPagamento() {
        Pagamento pagamento = new Pagamento(1L, BigDecimal.valueOf(32.25), "Bach",
                "2365145936541245", "07/28", "585", Status.CRIADO,
                1L, 2L);
        return pagamento;
    }

    public static PagamentoDTO createPagamentoDTO() {
        Pagamento pagamento = createPagamento();
        return new PagamentoDTO(pagamento);
    }

    public static PagamentoDTO createNewPagamentoDTO() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);
        return new PagamentoDTO(pagamento);
    }

}
