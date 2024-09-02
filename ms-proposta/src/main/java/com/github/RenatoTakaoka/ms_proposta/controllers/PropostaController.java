package com.github.RenatoTakaoka.ms_proposta.controllers;

import com.github.RenatoTakaoka.ms_proposta.dto.PropostaCreateDTO;
import com.github.RenatoTakaoka.ms_proposta.dto.PropostaDTO;
import com.github.RenatoTakaoka.ms_proposta.services.PropostaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @GetMapping
    public ResponseEntity<List<PropostaDTO>> findAll() {
        return ResponseEntity.ok(propostaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaDTO> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(propostaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PropostaDTO> create(@RequestBody @Valid PropostaCreateDTO dto) {
       PropostaDTO propostaDTO = propostaService.create(dto);
       return ResponseEntity.ok(propostaDTO);
    }

}
