package com.sicredi.desafiodigital.factory;

import com.sicredi.desafiodigital.domain.entity.VotoEntity;
import com.sicredi.desafiodigital.domain.model.VotoModel;

public class VotoFactory {

    public static final VotoEntity mapToEntity(String cpf, Integer codigoSessaoVotacao, String voto) {
        return VotoEntity
                .builder()
                .cpfAssociado(cpf)
                .codigoSessaoVotacao(codigoSessaoVotacao)
                .valor(voto)
                .build();
    }

    public static final VotoModel mapToModel(VotoEntity votoEntity) {
        return VotoModel
                .builder()
                .codigo(votoEntity.getCodigo())
                .cpfAssociado(votoEntity.getCpfAssociado())
                .codigoSessaoVotacao(votoEntity.getCodigoSessaoVotacao())
                .valor(votoEntity.getValor())
                .build();
    }
}
