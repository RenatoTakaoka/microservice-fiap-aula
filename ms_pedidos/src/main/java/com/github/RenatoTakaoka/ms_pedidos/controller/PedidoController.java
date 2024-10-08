package com.github.RenatoTakaoka.ms_pedidos.controller;

import com.github.RenatoTakaoka.ms_pedidos.dto.StatusDTO;
import com.github.RenatoTakaoka.ms_pedidos.services.PedidoService;
import com.github.RenatoTakaoka.ms_pedidos.dto.PedidoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/port")
    public ResponseEntity<String> getPort(@Value("${local.server.port}") String port) {
        String msg = String.format("Requisição da Instância recebida na porta: %s", port);
        return ResponseEntity.ok(msg);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> dto = pedidoService.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable @NotNull Long id) {
        PedidoDTO dto = pedidoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody @Valid PedidoDTO dto) {
        dto = pedidoService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovarPagamentoDoPedido(@PathVariable @NotNull Long id) {
        pedidoService.aprovarPagamentoDoPedido(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> updatePedidoStatus(@PathVariable Long id,
                                                        @RequestBody StatusDTO statusDTO) {
        PedidoDTO dto = pedidoService.updatePedidoStatus(id, statusDTO);
        return ResponseEntity.ok(dto);
    }

}
