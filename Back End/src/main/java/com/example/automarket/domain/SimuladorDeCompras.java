package com.example.automarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimuladorDeCompras {
    private Long id;
    private BigDecimal entrada;
    private Integer parcelas;
    private BigDecimal precoCarro;
    private BigDecimal valorFinanciamento;
    private BigDecimal valorParcela;
    private BigDecimal valorTotalPago;
}
