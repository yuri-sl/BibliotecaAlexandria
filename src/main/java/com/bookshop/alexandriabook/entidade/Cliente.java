package com.bookshop.alexandriabook.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String nome;
    @Column(nullable = true)
    private String sobrenome;
    @Column(nullable = true)
    private String cpf;
    @Column(nullable = true)
    private LocalDate aniversario;
    @Column(nullable = true)
    private String telefone;
    @Column(nullable = true)
    private String endereco;

    //Autenticação
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String username;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> listaPedidosUsuario = new ArrayList<>();


}
