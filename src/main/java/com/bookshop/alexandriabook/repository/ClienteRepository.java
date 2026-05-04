package com.bookshop.alexandriabook.repository;

import com.bookshop.alexandriabook.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente getClienteById(Long id);

    Object getAllById(Long id);
}
