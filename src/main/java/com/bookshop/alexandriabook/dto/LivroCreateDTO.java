package com.bookshop.alexandriabook.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.annotation.ApplicationScope;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApplicationScope
public class LivroCreateDTO {
    private String titulo;
    private String autor;
    private String descricao;
    private String genero;
    private double preco;
    private Integer estoque;
}
