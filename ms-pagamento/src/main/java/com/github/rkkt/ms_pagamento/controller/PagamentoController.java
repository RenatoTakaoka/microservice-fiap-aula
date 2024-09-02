package com.github.rkkt.ms_pagamento.controller;

import com.github.rkkt.ms_pagamento.dto.PagamentoDTO;
import com.github.rkkt.ms_pagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pagamentos")
@Tag(name = "Pagamentos", description = "Controller para pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

//    @Operation(summary = "Lista de pagamentos", description = "Encontra todos os pagamentos",
//            responses = {
//                    @ApiResponse(description = "Success", responseCode = "200"),
//                    @ApiResponse(description = "Bad Request", responseCode = "400"),
//                    @ApiResponse(description = "Not Found", responseCode = "404"),
//                    @ApiResponse(description = "Internal Error", responseCode = "500"),
//            })
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Page<PagamentoDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                                      @RequestParam(value = "size", defaultValue = "12") Integer size,
//                                                      @RequestParam(value = "direction", defaultValue = "asc") String direction) {
//        var sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nome"));
//        Page<PagamentoDTO> dto = service.findAll(pageable);
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> findAll() {
        List<PagamentoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Consulta um pagamento", description = "Encontra um pagamento pelo ID",
            tags = {"Pagamentos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PagamentoDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        PagamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    // Para endpoint protegido
    // @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Adiciona um pagamento", description = "Adiciona um pagamento utilizando um JSON",
            tags = {"Pagamentos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PagamentoDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    @PostMapping(produces = "application/json")
    public ResponseEntity<PagamentoDTO> insert(@RequestBody @Valid PagamentoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pagamento", description = "Atualiza um pagamento pelo id",
            tags = {"Pagamentos"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagamentoDTO> update(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid PagamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "Exclui um pagamento", description = "Exclui um pagamento pelo id",
            tags = {"Pagamentos"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
