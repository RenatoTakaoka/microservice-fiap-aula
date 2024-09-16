package com.github.RenatoTakaoka.ms_pagamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    private String nome; // Nome do cartão
    private String numeroDoCartao; // XXXX XXXX XXXX XXXX
    private String validade; // Validade do cartão - MM/AA
    private String codigoDeSeguranca; // Código de segurança - XXX
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status; // Status do pagamento
    @Column(nullable = false)
    private Long pedidoId;
    @Column(nullable = false)
    private Long formaDePagamentoId; // 1 - Dinheiro  | 2 - Cartão | 3 - Pix
}
