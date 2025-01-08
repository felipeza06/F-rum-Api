package com.challenge.Forum.posts;

import java.time.LocalDateTime;

public record DadosCadastroTopico(String titulo, String mensagem, String curso, LocalDateTime dataCriacao) {
}
