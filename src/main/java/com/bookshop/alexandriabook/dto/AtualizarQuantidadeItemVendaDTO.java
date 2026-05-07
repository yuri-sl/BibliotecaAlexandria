package com.bookshop.alexandriabook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AtualizarQuantidadeItemVendaDTO {
    private Long idItemVenda;
    private Integer quantidade;
    private BigDecimal precoComDesconto;
}
