package com.bookshop.alexandriabook.repository;

import com.bookshop.alexandriabook.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<Cliente,Long> {

}
