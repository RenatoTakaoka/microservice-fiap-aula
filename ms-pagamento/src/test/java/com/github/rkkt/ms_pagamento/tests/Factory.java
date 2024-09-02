package com.github.rkkt.ms_pagamento.tests;

import com.github.rkkt.ms_pagamento.dto.PagamentoDTO;
import com.github.rkkt.ms_pagamento.model.Pagamento;
import com.github.rkkt.ms_pagamento.model.Status;

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

}
