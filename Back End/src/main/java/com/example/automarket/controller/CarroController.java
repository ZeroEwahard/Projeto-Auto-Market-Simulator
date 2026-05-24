package com.example.automarket.controller;

import com.example.automarket.dto.carro.*;
import com.example.automarket.service.CarroService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("carros")
public class CarroController {


    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhadosCarros> cadastrar(@RequestBody @Valid DadosCadastrosCarros dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhadosCarros carro = carroService.cadastrar(dados);
        var uri = uriBuilder.path("/carros/{id}").buildAndExpand(carro.id()).toUri();
        return ResponseEntity.created(uri).body(carro);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDeCarro>> listar(
        @ParameterObject
        Pageable pageable
) {
    return ResponseEntity.ok(carroService.listar(pageable));
}

    @DeleteMapping("/{id}")
    public ResponseEntity<DadosDetalhadosCarros> deletar(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhadosCarros> atualizar(@PathVariable Long id, @RequestBody @Valid DadosDeAtualizacao dados) {
        return ResponseEntity.ok(carroService.atualizar(id, dados));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DadosDetalhadosCarros> atualizarParcial(@PathVariable Long id, @RequestBody DadosAtualizacaoParcial dados) {
        return ResponseEntity.ok(carroService.atualizacaoParcial(id, dados));
    }
}
