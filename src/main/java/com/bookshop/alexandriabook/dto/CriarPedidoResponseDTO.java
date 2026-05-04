package com.bookshop.alexandriabook.dto;

import com.bookshop.alexandriabook.entidade.ItemVenda;
import com.bookshop.alexandriabook.entidade.Pedido;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CriarPedidoResponseDTO {
    private Long id;
    private BigDecimal precoTotal;
    private LocalDateTime dataVenda;

    private List<ItemVenda> itens;

    public static CriarPedidoResponseDTO converterEntidadeDTO(Pedido dados){
        return CriarPedidoResponseDTO.builder()
                .id(dados.getId())
                .precoTotal(dados.getPrecoTotal())
                .dataVenda(dados.getDataVenda())
                .itens(dados.getItens())
                .build();
    }
}
