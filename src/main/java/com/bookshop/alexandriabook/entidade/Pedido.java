package com.bookshop.alexandriabook.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Tb_pedido")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal precoTotal;
    private LocalDateTime dataVenda;

    @OneToMany(mappedBy = "pedido")//Mapeado pelo campo pedido na entidade de ItemVenda
    private List<ItemVenda> itens;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    // 0 -> Carrinho -> 1 -> Pedido pago
    private Integer rascunho;
    private String formaPagamento;



}
