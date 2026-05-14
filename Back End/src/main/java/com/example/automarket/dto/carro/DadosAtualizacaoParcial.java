package com.example.automarket.dto.carro;

import com.example.automarket.domain.Condicao;
import com.example.automarket.service.CarroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosAtualizacaoParcial(@Positive
                                      BigDecimal preco,
                                      @NotBlank
                                      String imagem,
                                      @NotNull
                                      Condicao condicao,
                                      @Valid
                                      CarroService.FichaTecnicaKm fiTecnica) {
}
