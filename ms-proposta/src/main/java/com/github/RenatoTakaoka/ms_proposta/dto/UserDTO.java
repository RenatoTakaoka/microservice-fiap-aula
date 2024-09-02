package com.github.RenatoTakaoka.ms_proposta.dto;

import com.github.RenatoTakaoka.ms_proposta.models.Proposta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
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

}
