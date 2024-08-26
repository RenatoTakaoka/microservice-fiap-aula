package com.github.rkkt.ms_pedidos.repositories;

import com.github.rkkt.ms_pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
