package com.example.automarket.exceptions;

public class Erros {

    public static class LimiteDeCarroException extends RuntimeException {
        public LimiteDeCarroException(String mensagem) {
            super(mensagem);
        }
    }

    public static class ConteudoNaoEncontrado extends RuntimeException {
        public ConteudoNaoEncontrado(String mensagem) {
            super(mensagem);
        }
    }

    public static class ErroDeValidacao extends RuntimeException {
        public ErroDeValidacao(String mensagem) {
            super(mensagem);
        }
    }
}
