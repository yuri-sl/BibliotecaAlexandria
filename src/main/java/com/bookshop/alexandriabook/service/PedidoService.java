package com.bookshop.alexandriabook.service;


import com.bookshop.alexandriabook.dto.CriarPedidoRequestDTO;
import com.bookshop.alexandriabook.dto.CriarPedidoResponseDTO;
import com.bookshop.alexandriabook.entidade.Cliente;
import com.bookshop.alexandriabook.entidade.ItemVenda;
import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.entidade.Pedido;
import com.bookshop.alexandriabook.repository.ClienteRepository;
import com.bookshop.alexandriabook.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {
    final PedidoRepository pedidoRepository;
    final ClienteRepository clienteRepository;

    final LivroService livroService;
    final ItemVendaService itemVendaService;


    public Pedido encontrarPedidoPorId(Long idPedido) throws EntityNotFoundException{
        var pedidoAchado = pedidoRepository.findById(idPedido).orElseThrow(EntityNotFoundException::new);
        return pedidoAchado;
    }
    public CriarPedidoResponseDTO criarPedido(Long idPedido,CriarPedidoRequestDTO dados){
        BigDecimal totalCompra = BigDecimal.ZERO;
        List<Livro> listaLivrosComprados = livroService.buscarDadosLivros(dados.getListaProdutos());

        Pedido pedido = encontrarPedidoPorId(idPedido);

        List<ItemVenda> listaItensComprados = new ArrayList<>();

        Cliente cliente = clienteRepository.getClienteById(dados.getIdCliente());

        for(Livro liv : listaLivrosComprados){
            ItemVenda itemVenda = itemVendaService.transformarItemVenda(pedido,liv,1);
            listaItensComprados.add(itemVenda);
            totalCompra = totalCompra.add(liv.getPrecoAtualizado());
        }

        Pedido pedidoNovo = Pedido.builder()
                .precoTotal(totalCompra)
                .dataVenda(LocalDateTime.now())
                .itens(listaItensComprados)
                .cliente(cliente)
                .rascunho(1)
                .formaPagamento("PIX")
                .build();

        return CriarPedidoResponseDTO.converterEntidadeDTO(pedidoNovo);
    }


    public CriarPedidoResponseDTO adicionarItemCarrinho(Long idPedido,Long idLivro, Integer quantidade){

        Pedido pedido = encontrarPedidoPorId(idPedido);
        Livro livroComprado = livroService.buscarDadosLivroPorId(idLivro);
        ItemVenda itemVenda = itemVendaService.transformarItemVenda(pedido,livroComprado,quantidade);


        BigDecimal totalCompra = pedido.getPrecoTotal();
        List<ItemVenda> listaItensVendidos = pedido.getItens();
        listaItensVendidos.add(itemVenda);
        totalCompra = totalCompra.add(itemVenda.getPrecoHoraCompra());

        pedido.setItens(listaItensVendidos);
        pedido.setPrecoTotal(totalCompra);

        pedidoRepository.save(pedido);

        return CriarPedidoResponseDTO.converterEntidadeDTO(pedido);
    }

    public CriarPedidoResponseDTO criarCarrinho(Long userID){
        Cliente clienteEncontrado = clienteRepository.getClienteById(userID);

        Pedido carrinho = Pedido.builder()
                .precoTotal(BigDecimal.valueOf(0))
                .dataVenda(LocalDateTime.now())
                .cliente(clienteEncontrado)
                .rascunho(0)
                .build();

        pedidoRepository.save(carrinho);

        return CriarPedidoResponseDTO.converterEntidadeDTO(carrinho);
    }



}
