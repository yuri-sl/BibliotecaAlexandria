package com.bookshop.alexandriabook.dto;


import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.repository.LivroRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private double preco;
    private String genero;
    private String descricao;
    private Integer estoque;
    private BigDecimal precoAtualizado;

    public static LivroResponseDTO converterEntidadeDTO(Livro livro){
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .preco(livro.getPreco())
                .genero(livro.getGenero())
                .descricao(livro.getDescricao())
                .estoque(livro.getEstoque())
                .precoAtualizado(livro.getPrecoAtualizado())
                .build();
    }
}