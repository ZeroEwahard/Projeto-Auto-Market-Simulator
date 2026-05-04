package com.example.automarket.service;

import com.example.automarket.domain.Carro;
import com.example.automarket.domain.SimuladorDeCompras;
import com.example.automarket.dto.simuladorDeCompra.DadosDetalhamentosSimulador;
import com.example.automarket.dto.simuladorDeCompra.DadosSimulador;
import com.example.automarket.exceptions.Erros;
import com.example.automarket.repository.CarroRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class SimuladorCompraService {

    private final CarroRepository repositorio;
    private final BigDecimal taxaJuros;

    public SimuladorCompraService(CarroRepository repositorio, @Value("${simulador.taxa-juros}") BigDecimal taxaJuros) {
        this.repositorio = repositorio;
        this.taxaJuros = taxaJuros;
    }

    public DadosDetalhamentosSimulador simularCompra(DadosSimulador dados) {
        Carro carro = repositorio.findById(dados.carroId()).orElseThrow(() -> new Erros.ConteudoNaoEncontrado("Carro não encontrado"));

        BigDecimal preco = carro.getPreco();
        BigDecimal entrada = dados.entrada();
        Integer parcelas = getValidacao(dados, entrada, preco);

        BigDecimal valorFinanciado = preco.subtract(entrada);
        BigDecimal valorParcela = calcularPrice(valorFinanciado, taxaJuros, parcelas);
        BigDecimal valorTotal = valorParcela.multiply(BigDecimal.valueOf(parcelas));

        SimuladorDeCompras simulador = new SimuladorDeCompras();
        simulador.setId(dados.carroId());
        simulador.setEntrada(entrada);
        simulador.setParcelas(parcelas);
        simulador.setPrecoCarro(preco);
        simulador.setValorFinanciamento(valorFinanciado);
        simulador.setValorTotalPago(valorTotal);
        simulador.setValorParcela(valorParcela);

        return new DadosDetalhamentosSimulador(simulador.getId(), simulador.getEntrada(), simulador.getParcelas(),
                simulador.getPrecoCarro(), simulador.getValorFinanciamento(), simulador.getValorParcela(), simulador.getValorTotalPago());
    }

    private BigDecimal calcularPrice(BigDecimal valorFinanciado, BigDecimal taxaJuros, Integer parcelas) {
        MathContext mc = new MathContext(12, RoundingMode.HALF_UP);

        BigDecimal umMaisI = BigDecimal.ONE.add(taxaJuros);
        BigDecimal fator = umMaisI.pow(parcelas, mc);

        BigDecimal numerador = valorFinanciado
                .multiply(taxaJuros, mc)
                .multiply(fator, mc);

        BigDecimal denominador = fator.subtract(BigDecimal.ONE, mc);
        return numerador.divide(denominador, 2, RoundingMode.HALF_UP);
    }

    private @NonNull Integer getValidacao(DadosSimulador dados, BigDecimal entrada, BigDecimal preco) {
        Integer parcelas = dados.parcelas();

        if (entrada == null || parcelas == null) {
            throw new Erros.ErroDeValidacao("Entrada e parcelas são obrigatórias");
        }

        if (entrada.compareTo(BigDecimal.ZERO) < 0 || entrada.compareTo(preco) > 0) {
            throw new Erros.ErroDeValidacao("Entrada inválida");
        }

        if (parcelas <= 0) {
            throw new Erros.ErroDeValidacao("Parcelas deve ser maior que zero");
        }

        return parcelas;
    }
}
