package com.bookshop.alexandriabook.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CriarPedidoRequestDTO {
    private List<Long> listaProdutos;
    private Long idCliente;
}
