package com.example.automarket.dto.carro;

import com.example.automarket.domain.Carro;
import com.example.automarket.domain.Condicao;
import com.example.automarket.domain.FichaTecnica;

import java.math.BigDecimal;

public record DadosListagemDeCarro(Long id, String modelo, String marca, Integer ano, String tipo, BigDecimal preco, Condicao condicao, String imagem, FichaTecnica fichaTecnica) {

    public DadosListagemDeCarro(Carro carro) {
        this(carro.getId(), carro.getModelo(), carro.getMarca(), carro.getAno(), carro.getTipo(), carro.getPreco(), carro.getCondicao(), carro.getImagem(), carro.getFichaTecnica());
    }
}
