package com.bookshop.alexandriabook.service;


import com.bookshop.alexandriabook.dto.ClienteCreateDTO;
import com.bookshop.alexandriabook.dto.ClienteEditDTO;
import com.bookshop.alexandriabook.entidade.Cliente;
import com.bookshop.alexandriabook.exceptions.AlexandriaBookException;
import com.bookshop.alexandriabook.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class ClienteService {
    final ClienteRepository clienteRepository;


    public String criarCliente(ClienteCreateDTO dados){
        Cliente cliente = Cliente.builder()
                        .email(dados.getEmail())
                        .username(dados.getUsername())
                        .password(dados.getPassword())
                .build();
        clienteRepository.save(cliente);
        return "Cliente criado com sucesso!";
    }

    public List<ClienteCreateDTO> buscarTodosClientes(){
        List<Cliente> listaClientesEncontrados = clienteRepository.findAll().stream().toList();

        List<ClienteCreateDTO> listaClientesDTO = listaClientesEncontrados.stream()
                        .map(cli -> ClienteCreateDTO.converterEntidadeDTO(cli))
                                .toList();

        return  listaClientesDTO;
    }

    public String atualizarDadosCliente(Long idCliente,ClienteEditDTO dados){
        var cliente = clienteRepository.findById(idCliente).orElseThrow(EntityNotFoundException::new);

        cliente.setAniversario(dados.getAniversario());
        cliente.setNome(dados.getNome());
        cliente.setCpf(dados.getCpf());
        cliente.setEndereco(dados.getEndereco());
        cliente.setSobrenome(dados.getSobrenome());
        cliente.setTelefone(dados.getTelefone());

        clienteRepository.save(cliente);

        return "Cliente atualizado com sucesso";
    }

    public String atualizarCredenciais(Long idCLinte, ClienteEditDTO dados){
        var cliente = clienteRepository.findById(idCLinte).orElseThrow(EntityNotFoundException::new);

        cliente.setUsername(dados.getUsername());
        cliente.setPassword(dados.getPassword());

        return "Credenciais atualizadas com sucesso";

    }

    public String deletarUsuarioPorId(Long idCliente){
        var cliente = clienteRepository.findById(idCliente).orElseThrow(EntityNotFoundException::new);

        clienteRepository.deleteById(idCliente);
        return "Usuário deletado";
    }

    public List<Cliente> buscarTodoMundo(){
        return clienteRepository.findAll().stream().toList();
    }

    public Cliente buscarClientePorId(Long idCliente){
        return clienteRepository.getClienteById(idCliente);
    }

}
