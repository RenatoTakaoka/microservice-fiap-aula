package com.github.RenatoTakaoka.ms_pedidos.repositories;

import com.github.RenatoTakaoka.ms_pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
