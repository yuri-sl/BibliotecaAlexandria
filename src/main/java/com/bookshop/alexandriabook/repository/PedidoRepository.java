package com.bookshop.alexandriabook.repository;

import com.bookshop.alexandriabook.entidade.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    Long id(Long id);
}
