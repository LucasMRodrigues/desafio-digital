package com.sicredi.desafiodigital.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AssociadoDto {

    private String cpf;
    private String nome;
}
