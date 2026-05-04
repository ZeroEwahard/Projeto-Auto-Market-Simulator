package com.example.automarket.dto.carro;

import com.example.automarket.domain.Condicao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosCadastrosCarros(@NotBlank
                                   String marca,
                                   @NotBlank
                                   String modelo,
                                   @NotBlank
                                   String tipo,
                                   @NotNull
                                   Integer ano,
                                   @NotNull
                                   @Positive
                                   BigDecimal preco,
                                   @NotNull
                                   Condicao condicao,
                                   @NotBlank
                                   String imagem,
                                   @NotNull
                                   @Valid
                                   DadosFichaTecnica fichaTecnica) {
}
