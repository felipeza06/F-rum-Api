package com.challenge.Forum.posts;

import java.time.LocalDateTime;

public record DadosListagemTopicos(Long id, String titulo, String mensagem, String curso, LocalDateTime dataCriacao) {

    public DadosListagemTopicos(Topico topicos) {
        this(topicos.getId(), topicos.getTitulo(), topicos.getMensagem(), topicos.getCurso(), topicos.getDataCriacao());
    }

}
