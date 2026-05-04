package com.example.automarket.dto.simuladorDeCompra;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosSimulador(@NotNull
                             Long carroId,
                             @NotNull
                             BigDecimal entrada,
                             @NotNull
                             Integer parcelas) {
}
