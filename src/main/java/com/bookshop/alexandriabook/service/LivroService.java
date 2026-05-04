package com.bookshop.alexandriabook.service;


import com.bookshop.alexandriabook.dto.LivroCreateDTO;
import com.bookshop.alexandriabook.dto.LivroResponseDTO;
import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.exceptions.AlexandriaBookException;
import com.bookshop.alexandriabook.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
@AllArgsConstructor
public class LivroService {
    private LivroRepository repository;


    public List<LivroResponseDTO> buscarTodosLivros(){
        List<Livro> livrosEncontradosRepository = repository.findAll().stream().toList();
        List<LivroResponseDTO> listaLivros = livrosEncontradosRepository.stream()
                .map(liv -> LivroResponseDTO.converterEntidadeDTO(liv))
                .toList();
        return listaLivros;
    }


    public LivroResponseDTO buscarLivroDTOPorId(Long livroId){
        var livroDTO = repository.findById(livroId).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        return LivroResponseDTO.converterEntidadeDTO(livroDTO);
    }

    public Livro buscarDadosLivroPorId(Long livroId){
        var livroAchado = repository.findById(livroId).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        return livroAchado;
    }
    public LivroResponseDTO criarLivro(LivroCreateDTO dados) throws AlexandriaBookException {
        Livro livro = Livro.builder()
                .titulo(dados.getTitulo())
                .autor(dados.getAutor())
                .genero(dados.getGenero())
                .descricao(dados.getDescricao())
                .preco(dados.getPreco())
                .estoque(dados.getEstoque())
                .build();

//        List<Livro> livrosEncontrados = repository.buscarLivroPorNome(dados.getTitulo());

        repository.save(livro);
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .preco(livro.getPreco())
                .genero(livro.getGenero())
                .descricao(livro.getDescricao())
                .estoque(livro.getEstoque())
                .build();
    }

    public void deletarLivro(Long idLivro){
        var livro = repository.findById(idLivro).orElseThrow(() -> new EntityNotFoundException("Livro não existe"));
        repository.deleteById(idLivro);
    }

    public LivroResponseDTO editarPorId(Long idLivro,LivroCreateDTO dados){
        var livro = repository.findById(idLivro).orElseThrow(() -> new EntityNotFoundException("Livro não existe"));

        livro.setAutor(dados.getAutor());
        livro.setTitulo(dados.getTitulo());
        livro.setEstoque(dados.getEstoque());
        livro.setDescricao(dados.getDescricao());
        livro.setGenero(dados.getGenero());
        livro.setPreco(dados.getPreco());
        livro.setPrecoAtualizado(dados.getPrecoAtualizado());


        repository.save(livro);
        LivroResponseDTO respostaLivro = LivroResponseDTO.converterEntidadeDTO(livro);

        return respostaLivro;
    }

    public List<Livro> buscarDadosLivros(List<Long> listaIdsLivros){
        List<Livro> listaLivros = new ArrayList<>();

        for(Long id: listaIdsLivros){
            Livro livro = buscarDadosLivroPorId(id);
            listaLivros.add(livro);
        }
        return listaLivros;
    }







}
