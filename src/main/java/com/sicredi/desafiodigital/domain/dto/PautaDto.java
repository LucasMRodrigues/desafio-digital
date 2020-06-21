package com.sicredi.desafiodigital.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PautaDto {

    private Integer codigo;
    private String nome;
    private Integer totalVotosSim;
    private Integer totalVotosNao;
}
