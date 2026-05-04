package com.bookshop.alexandriabook.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteEditDTO {
    private Long id;
    private String username;
    private String password;
    private String email;

    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate aniversario;
    private String telefone;
    private String endereco;
}
