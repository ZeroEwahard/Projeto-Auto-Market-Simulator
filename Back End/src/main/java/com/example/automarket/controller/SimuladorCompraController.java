package com.example.automarket.controller;

import com.example.automarket.dto.simuladorDeCompra.DadosDetalhamentosSimulador;
import com.example.automarket.dto.simuladorDeCompra.DadosSimulador;
import com.example.automarket.service.SimuladorCompraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class SimuladorCompraController {

    private final SimuladorCompraService repositorio;

    public SimuladorCompraController(SimuladorCompraService repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentosSimulador> simular(@RequestBody @Valid DadosSimulador dados) {
        DadosDetalhamentosSimulador resultado = repositorio.simularCompra(dados);
        return ResponseEntity.ok(resultado);
    }
}
