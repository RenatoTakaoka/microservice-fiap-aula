package com.github.rkkt.ms_cep.dto;

import com.github.rkkt.ms_cep.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public EnderecoDTO(Endereco endereco) {
        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        localidade = endereco.getLocalidade();
        uf = endereco.getUf();
    }

}
