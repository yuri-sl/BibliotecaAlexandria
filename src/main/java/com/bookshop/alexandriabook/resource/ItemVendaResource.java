package com.bookshop.alexandriabook.resource;


import com.bookshop.alexandriabook.dto.AtualizarCarrinhoDTO;
import com.bookshop.alexandriabook.dto.AtualizarQuantidadeItemVendaDTO;
import com.bookshop.alexandriabook.service.ItemVendaService;
import com.bookshop.alexandriabook.service.PedidoService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemVendaResource {
    final ItemVendaService itemVendaService;

    @PutMapping("/{idPedido}")
    public ResponseEntity alterarQuantidadeItem(@PathVariable("idPedido") Long idPedido,
                                                @RequestBody AtualizarQuantidadeItemVendaDTO dados){
        try {
            return ResponseEntity.status(201).body(itemVendaService.alterarQuantidadeItem(idPedido,dados));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

//    @GetMapping("/{idPedido}")
//    public ResponseEntity<?> buscarPedido(@PathVariable("idPedido") Long idPedido){
//        try{
//            return ResponseEntity.status(200).body(pedidoService.encontrarPedidoPorId(idPedido));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }

}
