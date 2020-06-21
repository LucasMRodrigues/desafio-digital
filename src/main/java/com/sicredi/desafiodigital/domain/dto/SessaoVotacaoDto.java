package com.sicredi.desafiodigital.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class SessaoVotacaoDto {

    private Integer  codigo;
    private PautaDto pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
