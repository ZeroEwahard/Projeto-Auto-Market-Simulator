package com.example.automarket.service;

import com.example.automarket.domain.Carro;
import com.example.automarket.domain.FichaTecnica;
import com.example.automarket.dto.carro.*;
import com.example.automarket.exceptions.Erros;
import com.example.automarket.repository.CarroRepository;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
@Transactional
public class CarroService {

    private final CarroRepository carroRepository;
    private final long maxCarros;

    public CarroService(CarroRepository carroRepository, @Value("${carros.max}") long maxCarros) {
        this.carroRepository = carroRepository;
        this.maxCarros = maxCarros;
    }

    public DadosDetalhadosCarros cadastrar(DadosCadastrosCarros dados) {

        if (carroRepository.countWithLock() >= maxCarros) {
            throw new Erros.LimiteDeCarroException("Limite de carros alcançado! Não será mais possível cadastrar outros carros");
        }

        DadosFichaTecnica fichaTecnica = dados.fichaTecnica();

        Carro carro = getCarro(dados, fichaTecnica);
        carroRepository.save(carro);

        return new DadosDetalhadosCarros(carro);
    }

    public Page<DadosListagemDeCarro> listar(Pageable pageable) {
        return carroRepository.findAll(pageable)
                .map(DadosListagemDeCarro::new);
    }

    public void deletar(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new Erros.ConteudoNaoEncontrado("Carro não encontrado:" + id);
        }
        carroRepository.deleteById(id);
    }

    public DadosDetalhadosCarros atualizar(Long id, DadosDeAtualizacao dados) {
        Carro carro = carroRepository.findById(id).orElseThrow(() ->
                new Erros.ConteudoNaoEncontrado("Carro não encontrado"));

        seNaoForNulo(dados.marca(), carro::setMarca);
        seNaoForNulo(dados.modelo(), carro::setModelo);
        seNaoForNulo(dados.tipo(), carro::setTipo);
        seNaoForNulo(dados.ano(), carro::setAno);
        seNaoForNulo(dados.preco(), carro::setPreco);
        seNaoForNulo(dados.condicao(), carro::setCondicao);
        seNaoForNulo(dados.imagem(), img -> {
            if (img.startsWith("http")) {
                img = img.substring(img.lastIndexOf("/") + 1);
            }
            carro.setImagem(img);
        });
        seNaoForNulo(dados.fichaTecnica(), ficha ->
                carro.setFichaTecnica(converter(ficha)));

        carroRepository.save(carro);
        return new DadosDetalhadosCarros(carro);
    }

    public DadosDetalhadosCarros atualizacaoParcial(Long id, DadosAtualizacaoParcial dados) {
        Carro carro = carroRepository.findById(id).orElseThrow(() ->
                new Erros.ConteudoNaoEncontrado("Carro não encontrado"));

        seNaoForNulo(dados.preco(), carro::setPreco);
        seNaoForNulo(dados.condicao(), carro::setCondicao);
        seNaoForNulo(dados.imagem(), img -> {
            if (img.startsWith("http")) {
                img = img.substring(img.lastIndexOf("/") + 1);
            }
            carro.setImagem(img);
        });
        if (dados.fiTecnica() != null) {
            FichaTecnica ficha = carro.getFichaTecnica();
            if (ficha != null) {
                seNaoForNulo(dados.fiTecnica().getKm(), ficha::setKm);
            }
        }

        carroRepository.save(carro);
        return new DadosDetalhadosCarros(carro);
    }


    private @NonNull Carro getCarro(DadosCadastrosCarros dados, DadosFichaTecnica fichaTecnica) {
        FichaTecnica ficha = new FichaTecnica();
        ficha.setKm(fichaTecnica.km());
        ficha.setCombustivel(fichaTecnica.combustivel());
        ficha.setTransmissao(fichaTecnica.transmissao());
        ficha.setMotor(fichaTecnica.motor());
        ficha.setPotencia(fichaTecnica.potencia());
        ficha.setTorque(fichaTecnica.torque());
        ficha.setConsumo(fichaTecnica.consumo());

        Carro carro = new Carro();
        carro.setMarca(dados.marca());
        carro.setModelo(dados.modelo());
        carro.setTipo(dados.tipo());
        carro.setAno(dados.ano());
        carro.setPreco(dados.preco());
        carro.setCondicao(dados.condicao());
        if (dados.imagem() != null) {
            carro.setImagem(dados.imagem());
        }
        carro.setFichaTecnica(ficha);
        return carro;
    }

    private <T> void seNaoForNulo(T valor, Consumer<T> setter) {
        if (valor != null) {
            setter.accept(valor);
        }
    }

    private FichaTecnica converter(DadosFichaTecnica ficha) {
        return new FichaTecnica(
                ficha.km(),
                ficha.combustivel(),
                ficha.transmissao(),
                ficha.motor(),
                ficha.potencia(),
                ficha.torque(),
                ficha.consumo()
        );
    }


    @Setter
    @Getter
    public static class FichaTecnicaKm {
        private Integer km;
    }
}


