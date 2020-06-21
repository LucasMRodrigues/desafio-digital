package com.sicredi.desafiodigital.api.impl;

import com.sicredi.desafiodigital.api.SessaoVotacaoApi;
import com.sicredi.desafiodigital.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SessaoVotacaoApiImpl implements SessaoVotacaoApi {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Override
    public ResponseEntity<Integer> criarSessaoDeVotacao(
            Integer codigoPauta,
            LocalDateTime dataIinicio,
            LocalDateTime dataFim
    ) {
        var codigoSessaoVotacaoCriada = sessaoVotacaoService.criarSessaoVotacao(
                codigoPauta, dataIinicio, dataFim);

        return new ResponseEntity(codigoSessaoVotacaoCriada, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obterResultadoDaSessaoDeVotacao(Integer codigoSessaoVotacao) {

        var mensagem = sessaoVotacaoService.obterResultadoDaSessaoDeVotacao(codigoSessaoVotacao);

        return new ResponseEntity(mensagem, HttpStatus.OK);
    }
}
