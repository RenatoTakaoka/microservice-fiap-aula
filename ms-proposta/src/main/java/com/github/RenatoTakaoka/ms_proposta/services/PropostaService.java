package com.github.RenatoTakaoka.ms_proposta.services;

import com.github.RenatoTakaoka.ms_proposta.dto.PropostaCreateDTO;
import com.github.RenatoTakaoka.ms_proposta.dto.PropostaDTO;
import com.github.RenatoTakaoka.ms_proposta.models.Proposta;
import com.github.RenatoTakaoka.ms_proposta.models.User;
import com.github.RenatoTakaoka.ms_proposta.repositories.PropostaRepository;
import com.github.RenatoTakaoka.ms_proposta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Transactional(readOnly = true)
    public List<PropostaDTO> findAll() {
        return propostaRepository.findAll().stream()
                .map(proposta -> {
                    PropostaDTO dto = new PropostaDTO();
                    dto.setId(proposta.getId());
                    dto.setValorSolicitado(proposta.getValorSolicitado());
                    dto.setPrazoParaPagamento(proposta.getPrazoParaPagamento());
                    dto.setAprovado(proposta.getAprovado());
                    dto.setUserId(proposta.getUser().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public PropostaDTO findById(Long id) {
        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        PropostaDTO dto = new PropostaDTO();
        dto.setId(proposta.getId());
        dto.setValorSolicitado(proposta.getValorSolicitado());
        dto.setPrazoParaPagamento(proposta.getPrazoParaPagamento());
        dto.setAprovado(proposta.getAprovado());
        dto.setUserId(proposta.getUser().getId());

        return dto;
    }

    @Transactional
    public PropostaDTO create(PropostaCreateDTO dto) {

        User user = new User();
        user.setNome(dto.getNome());
        user.setSobrenome(dto.getSobrenome());
        user.setCpf(dto.getCpf());
        user.setTelefone(dto.getTelefone());
        user.setRenda(dto.getRenda());

        Proposta proposta = new Proposta();
        proposta.setValorSolicitado(dto.getValorSolicitado());
        proposta.setPrazoParaPagamento(dto.getPrazoParaPagamento());
        proposta.setAprovado(false);
        proposta.setUser(user);

        proposta = propostaRepository.save(proposta);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.setId(proposta.getId());
        propostaDTO.setValorSolicitado(proposta.getValorSolicitado());
        propostaDTO.setPrazoParaPagamento(proposta.getPrazoParaPagamento());
        propostaDTO.setAprovado(proposta.getAprovado());
        propostaDTO.setUserId(proposta.getUser().getId());

        return propostaDTO;
    }

}
