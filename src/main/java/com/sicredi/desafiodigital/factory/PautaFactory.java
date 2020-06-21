package com.sicredi.desafiodigital.factory;

import com.sicredi.desafiodigital.domain.entity.PautaEntity;
import com.sicredi.desafiodigital.domain.model.PautaModel;

public class PautaFactory {

    public static final PautaEntity mapToEntity(String nomePauta) {
        return PautaEntity
                .builder()
                .nome(nomePauta)
                .build();
    }

    public static final PautaModel mapToModel(PautaEntity pauta) {
        return PautaModel
                .builder()
                .codigo(pauta.getCodigo())
                .nome(pauta.getNome())
                .build();
    }
}
