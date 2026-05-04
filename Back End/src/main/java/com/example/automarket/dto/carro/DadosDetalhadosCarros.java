package com.example.automarket.dto.carro;

import com.example.automarket.domain.Carro;
import com.example.automarket.domain.Condicao;
import com.example.automarket.domain.FichaTecnica;

import java.math.BigDecimal;

public record DadosDetalhadosCarros(Long id, String marca, String modelo, String tipo,
                                    Integer ano, BigDecimal preco, Condicao condicao, String imagem, FichaTecnica fichaTecnica) {

    private static final String URL = "http://localhost:8080/";

    public DadosDetalhadosCarros(Carro carros) {
        this(carros.getId(), carros.getMarca(), carros.getModelo(), carros.getTipo(), carros.getAno(),
                carros.getPreco(), carros.getCondicao(), imgURL(carros.getImagem()), carros.getFichaTecnica());
    }

    private static String imgURL(String imagem) {
        if (imagem == null || imagem.isBlank()) {
            return null;
        }
        return URL + imagem;
    }
}