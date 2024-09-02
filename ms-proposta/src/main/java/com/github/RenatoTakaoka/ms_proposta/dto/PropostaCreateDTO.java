package com.github.RenatoTakaoka.ms_proposta.dto;

import jakarta.validation.constraints.NotBlank;
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
public class PropostaCreateDTO {

    @NotBlank(message = "Campo requerido")
    private String nome;
    @NotBlank(message = "Campo requerido")
    private String sobrenome;
    @NotBlank(message = "Campo requerido")
    private String cpf;
    @NotBlank(message = "Campo requerido")
    private String telefone;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Renda deve ser um numero positivo")
    private BigDecimal renda;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Valor solicitado deve ser um numero positivo")
    private BigDecimal valorSolicitado;
    @NotNull(message = "Campo requerido")
    @Positive(message = "Prazo para pagamento deve ser um numero positivo")
    private Integer prazoParaPagamento;

}
