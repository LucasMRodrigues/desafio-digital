package com.sicredi.desafiodigital.domain.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "VOTO")
public class VotoEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "CPF_ASSOCIADO")
    private String cpfAssociado;

    @Column(name = "ID_SESSAO_VOTACAO")
    private Integer codigoSessaoVotacao;

    @Column(name = "VALOR")
    private String valor;
}
