package com.bookshop.alexandriabook.entidade;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Tb_pedido")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal precoTotal;
    private LocalDateTime dataVenda;

    @OneToMany(mappedBy = "pedido")//Mapeado pelo campo pedido na entidade de ItemVenda
    private List<ItemVenda> itens;



}
