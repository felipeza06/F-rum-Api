package com.challenge.Forum.controller;

import com.challenge.Forum.posts.Topico;

public record DetalhamentoTopicos(Long id, String titulo, String mensagem, String curso) {

    public DetalhamentoTopicos (Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso());

    }

}
