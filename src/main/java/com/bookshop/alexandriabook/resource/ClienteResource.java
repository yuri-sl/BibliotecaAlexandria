package com.bookshop.alexandriabook.resource;


import com.bookshop.alexandriabook.dto.ClienteCreateDTO;
import com.bookshop.alexandriabook.dto.ClienteEditDTO;
import com.bookshop.alexandriabook.repository.ClienteRepository;
import com.bookshop.alexandriabook.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteResource {
    final ClienteService clienteService;
    private final ClienteRepository clienteRepository;


    @GetMapping("/fetch")
    public ResponseEntity<?> buscarTodos(){
        try{
            return ResponseEntity.status(201).body(clienteService.buscarTodoMundo());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    //Criar novo usuario
    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody ClienteCreateDTO dados){
        try{
            return ResponseEntity.status(201).body(clienteService.criarCliente(dados));
        } catch (RuntimeException e) {
            return  ResponseEntity.status(400).body("Erro ao criar cliente");
        }
    }

    //Buscar todos usuários
    @GetMapping
    public ResponseEntity<?> buscarTodosClientes(){
        try{
            return ResponseEntity.status(201).body(clienteService.buscarTodosClientes());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //Editar dados cliente
    @PutMapping("/edit/{idCliente}")
    public ResponseEntity<?> editarClliente(@PathVariable("idCliente") Long idCliente,
                                            @RequestBody ClienteEditDTO dados){
        try{
            return ResponseEntity.status(201).body(clienteService.atualizarDadosCliente(idCliente,dados));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erro ao atualizar");
        }
    }

    //Mudar senha
    @PutMapping("/auth/help/{idCliente}")
    public ResponseEntity<?> forgotPassword(@PathVariable("idCliente") Long idCliente,
                                            @RequestBody ClienteEditDTO dados){
        try{
            return ResponseEntity.status(201).body(clienteService.atualizarCredenciais(idCliente,dados));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erro ao atualizar credenciais");
        }
    }

    @DeleteMapping("/delete/{idCliente}")
    public ResponseEntity<?> deleteById(@PathVariable("idCliente") Long idCliente){
        try{
            return ResponseEntity.status(204).body(clienteService.deletarUsuarioPorId(idCliente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
