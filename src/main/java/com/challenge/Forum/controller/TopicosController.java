package com.challenge.Forum.controller;

import com.challenge.Forum.posts.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){

        var topico = new Topico(dados);
        repository.save(topico);

        System.out.println(topico.getDataCriacao());

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoTopicos(topico));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DadosListagemTopicos>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTopicos::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaTopico dados) {
        var topicos = repository.getReferenceById(dados.id());
        topicos.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhamentoTopicos(topicos));
    }

    @GetMapping("/{id}")
    public ResponseEntity delalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoTopicos(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

