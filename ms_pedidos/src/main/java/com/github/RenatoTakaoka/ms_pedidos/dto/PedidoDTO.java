package com.github.RenatoTakaoka.ms_pedidos.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {

   private Long id;
   private LocalDateTime dataHora;

   @Enumerated(EnumType.STRING)
   private StatusDTO status;

    private List<ItemDoPedidoDTO> itensDoPedido = new ArrayList<>();

}
