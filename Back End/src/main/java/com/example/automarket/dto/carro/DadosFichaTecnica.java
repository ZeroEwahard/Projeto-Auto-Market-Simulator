package com.example.automarket.dto.carro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosFichaTecnica(@NotNull
                                Integer km,
                                @NotBlank
                                String combustivel,
                                @NotBlank
                                String transmissao,
                                @NotBlank
                                String motor,
                                @NotNull
                                Integer potencia,
                                @NotNull
                                Double torque,
                                @NotNull
                                Double consumo) {

}
