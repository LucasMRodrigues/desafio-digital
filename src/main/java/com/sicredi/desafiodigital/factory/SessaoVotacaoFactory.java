package com.sicredi.desafiodigital.factory;

import com.sicredi.desafiodigital.domain.dto.PautaDto;
import com.sicredi.desafiodigital.domain.dto.SessaoVotacaoDto;
import com.sicredi.desafiodigital.domain.entity.SessaoVotacaoEntity;
import com.sicredi.desafiodigital.domain.model.SessaoVotacaoModel;

import java.time.LocalDateTime;

public class SessaoVotacaoFactory {

    public static final SessaoVotacaoEntity mapToEntity(
            Integer codigoPauta,
            LocalDateTime dataFim
    ) {
        return SessaoVotacaoEntity
                .builder()
                .codigoPauta(codigoPauta)
                .dataInicio(LocalDateTime.now())
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

    public static SessaoVotacaoDto mapToDto(SessaoVotacaoEntity sessaoVotacao, String nomePauta) {
        return SessaoVotacaoDto
                .builder()
                .codigo(sessaoVotacao.getCodigo())
                .nomePauta(nomePauta)
                .sessaoVotacaoEncerrada(sessaoVotacao.getDataFim().isBefore(LocalDateTime.now()))
                .dataFim(sessaoVotacao.getDataFim())
                .build();
    }
}
