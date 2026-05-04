package com.bookshop.alexandriabook.dto;


import com.bookshop.alexandriabook.entidade.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CienteResponseDTO {
    private Long id;
    private String email;
    private String password;

    public static CienteResponseDTO converterEntidadeDTO(Cliente cliente){
        return  CienteResponseDTO.builder()
                .id(cliente.getId())
                .email(cliente.getEmail())
                .password(cliente.getPassword())
                .build();
    }
}
