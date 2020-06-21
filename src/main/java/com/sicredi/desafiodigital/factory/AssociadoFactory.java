package com.sicredi.desafiodigital.factory;

import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import com.sicredi.desafiodigital.domain.entity.AssociadoEntity;
import com.sicredi.desafiodigital.domain.model.AssociadoModel;

public class AssociadoFactory {

    public static final AssociadoEntity mapToEntity(String cpf, String nome) {
        return AssociadoEntity
                .builder()
                .cpf(cpf)
                .nome(nome)
                .build();
    }

    public static final AssociadoDto mapToDto(AssociadoEntity associado) {
        return AssociadoDto.builder()
                .cpf(associado.getCpf())
                .nome(associado.getNome())
                .build();
    }

    public static final AssociadoModel mapToModel(AssociadoEntity associado) {
        return AssociadoModel.builder()
                .cpf(associado.getCpf())
                .nome(associado.getNome())
                .build();
    }
}
