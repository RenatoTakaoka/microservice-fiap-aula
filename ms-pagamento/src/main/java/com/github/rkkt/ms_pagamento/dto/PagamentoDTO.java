package com.github.rkkt.ms_pagamento.dto;

import com.github.rkkt.ms_pagamento.model.Pagamento;
import com.github.rkkt.ms_pagamento.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
public class PagamentoDTO {

    @Schema(description = "ID do pagamento gerado pelo banco de dados")
    private Long id;
    @NotNull(message = "Campo obrigatório")
    @Positive(message = "O valor deve ser positivo")
    @Schema(description = "Valor do pagamento")
    private BigDecimal valor;
    @Size(max = 100, message = "Máximo de 100 caracteres")
    @Schema(description = "Nome do pagamento")
    private String nome;
    @Size(max = 19, message = "Número do cartão deve conter no máximo 19 caracteres")
    @Schema(description = "Número do cartão de crédito")
    private String numeroDoCartao;
    @Size(min = 5, max = 5, message = "Validade do cartão deve ter 5 caracteres")
    @Schema(description = "Validade do cartão de crédito")
    private String validade;
    @Size(min = 3, max = 3, message = "Código de segurança deve ter 3 caracteres")
    @Schema(description = "Código de segurança do cartão de crédito")
    private String codigoDeSeguranca;
    @Enumerated(value = EnumType.STRING)
    @Schema(description = "Status do pedido")
    private Status status;
    @NotNull(message = "Pedido id é obrigatório")
    @Schema(description = "ID do pedido")
    @Positive(message = "O ID do pedido deve ser um número positivo")
    private Long pedidoId;
    @NotNull(message = "Forma de pagamento ID é obrigatório")
    @Schema(description = "Forma de pagamento 1 - Dinheiro  | 2 - Cartão | 3 - Pix")
    @Positive(message = "O ID do pagamento deve ser um número positivo")
    private Long formaDePagamento; // 1 - Dinheiro  | 2 - Cartão | 3 - Pix

    public PagamentoDTO(Pagamento entity) {
        id = entity.getId();
        valor = entity.getValor();
        nome = entity.getNome();
        numeroDoCartao = entity.getNumeroDoCartao();
        validade = entity.getValidade();
        codigoDeSeguranca = entity.getCodigoDeSeguranca();
        status = entity.getStatus();
        pedidoId = entity.getPedidoId();
        formaDePagamento = entity.getFormaDePagamentoId();
    }
}
