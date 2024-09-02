package com.github.RenatoTakaoka.ms_proposta.dto;

import com.github.RenatoTakaoka.ms_proposta.models.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropostaDTO {

    private Long id;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Valor solicitado deve ser um numero positivo")
    private BigDecimal valorSolicitado;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Prazo para pagamento deve ser um numero positivo")
    private Integer prazoParaPagamento;
    private Boolean aprovado;
    private Long userId;

}
