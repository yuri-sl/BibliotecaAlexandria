package com.bookshop.alexandriabook.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AtualizarCarrinhoDTO {
    private Long idLivro;
    private Integer quantidade;
}
