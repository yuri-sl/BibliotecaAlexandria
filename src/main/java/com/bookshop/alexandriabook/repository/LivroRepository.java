package com.bookshop.alexandriabook.repository;

import com.bookshop.alexandriabook.entidade.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    List<Livro> findByTitulo(String title);

    Long id(Long id);
}
