package com.example.automarket.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "carros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private String tipo;

    private Integer ano;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Condicao condicao;

    private String imagem;

    @Embedded
    private FichaTecnica fichaTecnica;

}
