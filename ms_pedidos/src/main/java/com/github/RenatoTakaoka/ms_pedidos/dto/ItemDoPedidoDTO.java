package com.github.RenatoTakaoka.ms_pedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDoPedidoDTO {

    private Long id;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Quantidade deve ser um numero positivo")
    private Integer quantidade;
    private String descricao;

}
