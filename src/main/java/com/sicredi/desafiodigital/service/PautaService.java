package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.model.PautaModel;
import com.sicredi.desafiodigital.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.sicredi.desafiodigital.factory.PautaFactory.mapToEntity;
import static com.sicredi.desafiodigital.factory.PautaFactory.mapToModel;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public PautaModel obterPautaPeloCodigo(Integer codigoPauta) {
        return mapToModel(pautaRepository.findByCodigo(codigoPauta));
    }

    public String obterNomeDaPautaPeloCodigo(Integer codigoPauta) {

        var pauta = pautaRepository.findByCodigo(codigoPauta);

        return Objects.nonNull(pauta) ? pauta.getNome() : null;
    }

    public Integer criarPauta(String nomePauta) {
        return pautaRepository.save(mapToEntity(nomePauta)).getCodigo();
    }
}
