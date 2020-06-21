package com.sicredi.desafiodigital.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoVotacaoModel {

    private Integer codigo;
    private Integer codigoPauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
