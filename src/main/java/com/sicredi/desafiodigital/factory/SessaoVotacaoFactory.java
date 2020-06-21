package com.sicredi.desafiodigital.factory;

import com.sicredi.desafiodigital.domain.entity.SessaoVotacaoEntity;
import com.sicredi.desafiodigital.domain.model.SessaoVotacaoModel;

import java.time.LocalDateTime;

public class SessaoVotacaoFactory {

    public static final SessaoVotacaoEntity mapToEntity(
            Integer codigoPauta,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        return SessaoVotacaoEntity
                .builder()
                .codigoPauta(codigoPauta)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();
    }

    public static final SessaoVotacaoModel mapToModel(SessaoVotacaoEntity sessaoVotacao) {
        return SessaoVotacaoModel
                .builder()
                .codigo(sessaoVotacao.getCodigo())
                .codigoPauta(sessaoVotacao.getCodigoPauta())
                .dataInicio(sessaoVotacao.getDataInicio())
                .dataFim(sessaoVotacao.getDataFim())
                .build();
    }
}
