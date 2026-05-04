package com.example.automarket.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FichaTecnica {

    private Integer km;
    private String combustivel;
    private String transmissao;
    private String motor;
    private Integer potencia;
    private Double torque;
    private Double consumo;
}
