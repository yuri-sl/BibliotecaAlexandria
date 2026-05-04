package com.bookshop.alexandriabook.service;


import com.bookshop.alexandriabook.entidade.ItemVenda;
import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.entidade.Pedido;
import com.bookshop.alexandriabook.repository.ItemVendaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemVendaService {
    final ItemVendaRepository itemVendaRepository;


    public ItemVenda transformarItemVenda(Pedido pedido, Livro livro,Integer quantidadeComprada){
        return ItemVenda.builder()
                .livroComprado(livro)
                .pedido(pedido)
                .quantidade(quantidadeComprada)
                .precoHoraCompra(livro.getPrecoAtualizado())
                .build();
    }






}
