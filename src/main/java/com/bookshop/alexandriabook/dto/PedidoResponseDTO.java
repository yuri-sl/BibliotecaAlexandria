package com.bookshop.alexandriabook.dto;

import com.bookshop.alexandriabook.entidade.ItemVenda;
import com.bookshop.alexandriabook.entidade.Pedido;
import com.bookshop.alexandriabook.repository.ItemVendaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PedidoResponseDTO {
    public Long id;
    public BigDecimal precoTotal;
    public List<ItemVenda> itensComprados;
    private Integer rascunho;

    public static PedidoResponseDTO converterEntidadeDTO(Pedido pedido){
        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .precoTotal(pedido.getPrecoTotal())
                .itensComprados(pedido.getItens())
                .rascunho(pedido.getRascunho())
                .build();
    }

}
