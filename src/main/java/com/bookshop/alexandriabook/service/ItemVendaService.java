package com.bookshop.alexandriabook.service;


import com.bookshop.alexandriabook.dto.AtualizarQuantidadeItemVendaDTO;
import com.bookshop.alexandriabook.dto.PedidoResponseDTO;
import com.bookshop.alexandriabook.entidade.ItemVenda;
import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.entidade.Pedido;
import com.bookshop.alexandriabook.repository.ItemVendaRepository;
import com.bookshop.alexandriabook.repository.PedidoRepository;
import com.bookshop.alexandriabook.resource.ItemVendaResource;
import com.bookshop.alexandriabook.resource.PedidoResource;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemVendaService {
    final PedidoRepository pedidoRepository;


    public ItemVenda transformarItemVenda(Pedido pedido, Livro livro,Integer quantidadeComprada){
        return ItemVenda.builder()
                .livroComprado(livro)
                .pedido(pedido)
                .quantidade(quantidadeComprada)
                .precoHoraCompra(livro.getPrecoAtualizado())
                .build();
    }

    public Pedido alterarQuantidadeItem(Long idPedido,
                                        AtualizarQuantidadeItemVendaDTO dados){
        Pedido pedido = pedidoRepository.getReferenceById(idPedido);
        ItemVenda itemVendaBuscado = pedido.getItens()
                .stream()
                .filter(item -> item.getId().equals(dados.getIdItemVenda()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado no pedido"));

        itemVendaBuscado.setQuantidade(dados.getQuantidade());
        return pedidoRepository.save(pedido);
    }









}
