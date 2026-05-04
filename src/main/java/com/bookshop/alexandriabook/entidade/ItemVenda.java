package com.bookshop.alexandriabook.entidade;

import com.bookshop.alexandriabook.dto.LivroResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_itens_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livroComprado;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_hora_compra",nullable = false,precision = 10,scale = 2)
    private BigDecimal precoHoraCompra;

}
