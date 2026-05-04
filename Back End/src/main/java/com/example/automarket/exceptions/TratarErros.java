package com.example.automarket.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratarErros {

    private static final Logger logger = LoggerFactory.getLogger(TratarErros.class);

    @ExceptionHandler(Erros.ConteudoNaoEncontrado.class)
    public ResponseEntity<ApiError> tratarErro404(Erros.ConteudoNaoEncontrado ex, HttpServletRequest request) {
        logger.warn("Entity not found: {}", ex.getMessage());

        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "Conteúdo não encontrado",
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Erros.ErroDeValidacao.class)
    public ResponseEntity<ApiError> tratarErro400(Erros.ErroDeValidacao ex, HttpServletRequest request) {
        logger.warn("Validation error: {}", ex.getMessage());

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Dados inválidos na requisição",
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> tratarErro405(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        logger.warn("Method not allowed: {}", ex.getMessage());

        ApiError error = new ApiError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Method Not Allowed",
                "Método HTTP não permitido",
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> tratarErro500(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled exception caught: {}", ex.getMessage(), ex);

        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Erro Inesperado no servidor",
                request.getRequestURI(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Erros.LimiteDeCarroException.class)
    public ResponseEntity<ApiError> tratarLimiteCarro(Erros.LimiteDeCarroException ex, HttpServletRequest request) {
        logger.warn("Limite de carro: {}", ex.getMessage(), ex);

        ApiError error = new ApiError(HttpStatus.TOO_MANY_REQUESTS.value(),
                "Too Many Request",
                "Limite de carros atigindo",
                request.getRequestURI(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(error);
    }
}
