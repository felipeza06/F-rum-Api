package com.challenge.Forum.posts;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaTopico(

        @NotNull
        Long id,
        String titulo,
        String mensagem

) {
}
