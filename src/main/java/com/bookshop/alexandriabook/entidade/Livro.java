package com.bookshop.alexandriabook.entidade;

import jakarta.persistence.*;
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
@Entity
@Table(name = "TB_LIVROS")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String titulo;
    @Column
    private String autor;
    @Column
    private String genero;
    @Column
    private String descricao;
    @Column
    private double preco;
    @Column
    private Integer estoque;

}
