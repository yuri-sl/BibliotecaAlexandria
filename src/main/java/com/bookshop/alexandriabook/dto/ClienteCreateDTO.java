package com.bookshop.alexandriabook.dto;


import com.bookshop.alexandriabook.entidade.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data


public class ClienteCreateDTO {
    private String email;
    private String password;
    private String username;

    public static ClienteCreateDTO converterEntidadeDTO(Cliente cliente){
        return  ClienteCreateDTO.builder()
                .email(cliente.getEmail())
                .username(cliente.getUsername())
                .password(cliente.getPassword())
                .build();
    }
}
