package com.github.rkkt.ms_cep.controller;

import com.github.rkkt.ms_cep.dto.EnderecoDTO;
import com.github.rkkt.ms_cep.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cep")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping(value = "/{cep}")
    public ResponseEntity<EnderecoDTO> findCep(@PathVariable String cep) {

        return ResponseEntity.ok(service.getCep(cep));

    }

}
