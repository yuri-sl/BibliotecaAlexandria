package com.bookshop.alexandriabook.resource;


import com.bookshop.alexandriabook.dto.LivroCreateDTO;
import com.bookshop.alexandriabook.dto.LivroResponseDTO;
import com.bookshop.alexandriabook.entidade.Livro;
import com.bookshop.alexandriabook.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Status;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/livro")
@RestController
public class LivroResource {
    final LivroService livroService;

    @GetMapping
    public ResponseEntity<?> fetchAllBooks(){
        try{
            List<LivroResponseDTO> listaLivros = livroService.buscarTodosLivros();
            return ResponseEntity.status(200).body(listaLivros);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erro inesperado");
        }
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<?> fetchBookById(@PathVariable("idLivro") Long idLivro){
        try{
            LivroResponseDTO livroEncontrado = livroService.buscarLivroDTOPorId(idLivro);
            return ResponseEntity.status(200).body(livroEncontrado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body("Não foi encontrado um livro");
        }
    }

    @PostMapping()
    public ResponseEntity<?> createNewBook(@RequestBody LivroCreateDTO dados){
        try{
            LivroResponseDTO livroResponseDTO = livroService.criarLivro(dados);
            return ResponseEntity.status(201).body(livroResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Dados inválidos");
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth(){
        try{
            return ResponseEntity.status(200).body("Vivo");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Morto");
        }
    }

    @DeleteMapping("/{idLivro}")
    public ResponseEntity<?> deletarPorId(@PathVariable("idLivro") Long idLivro){
        try{
            livroService.deletarLivro(idLivro);
            return ResponseEntity.status(204).body("Deletado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body("Não encontrado");
        }
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity<?> editarPorId(@PathVariable("idLivro") Long idLivro,@RequestBody LivroCreateDTO dados){
        try{
            LivroResponseDTO livroEditado =livroService.editarPorId(idLivro,dados);
            return ResponseEntity.status(201).body(livroEditado);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }



}
