package com.example.automarket.dto.simuladorDeCompra;

import com.example.automarket.domain.SimuladorDeCompras;

import java.math.BigDecimal;

public record DadosDetalhamentosSimulador(Long id,
                                          BigDecimal entrada,
                                          Integer parcelas,
                                          BigDecimal precoCarro,
                                          BigDecimal valorFinanciamento,
                                          BigDecimal valorParcela,
                                          BigDecimal valorTotalPago) {

    public DadosDetalhamentosSimulador(SimuladorDeCompras simulador) {
        this(simulador.getId(), simulador.getEntrada(), simulador.getParcelas(), simulador.getPrecoCarro(),
                simulador.getValorFinanciamento(), simulador.getValorParcela(), simulador.getValorTotalPago());
    }
}
