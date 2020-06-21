package com.sicredi.desafiodigital.domain.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoModel {

    private Integer codigo;
    private String  cpfAssociado;
    private Integer codigoSessaoVotacao;
    private String  valor;
}
