package com.bookshop.alexandriabook.entidade;

import jakarta.persistence.*;

@Entity
@Table(name = "Gerente")
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
}
