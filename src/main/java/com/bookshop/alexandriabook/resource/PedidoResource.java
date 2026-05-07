package com.bookshop.alexandriabook.resource;

import com.bookshop.alexandriabook.dto.AtualizarCarrinhoDTO;
import com.bookshop.alexandriabook.dto.CriarPedidoRequestDTO;
import com.bookshop.alexandriabook.service.PedidoService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class PedidoResource {
    final PedidoService pedidoService;


    @PostMapping("/carrinho/criar/{userId}")
    public ResponseEntity<?> criarCarrinho(@PathVariable("userId")
            Long userId){
        try{
            return ResponseEntity.status(201).body(pedidoService.criarCarrinho(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PostMapping("/carrinho/adicionar/{idPedido}")
    public ResponseEntity<?> adicionarItemCarrinho(@PathVariable("idPedido")
                                           Long idPedido, @RequestBody AtualizarCarrinhoDTO dados){
        try{
            return ResponseEntity.status(201).body(pedidoService.adicionarItemCarrinho(idPedido,dados.getIdLivro(),dados.getQuantidade()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }


    @PostMapping("/pagar/{idPedido}")
    public ResponseEntity<?> criarPedido(@PathVariable("idPedido")
            Long idPedido, @RequestBody CriarPedidoRequestDTO dados){
        try{
            return ResponseEntity.status(201).body(pedidoService.criarPedido(idPedido,dados));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<?> buscarPedido(@PathVariable("idPedido") Long idPedido){
        try{
            return ResponseEntity.status(201).body();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
