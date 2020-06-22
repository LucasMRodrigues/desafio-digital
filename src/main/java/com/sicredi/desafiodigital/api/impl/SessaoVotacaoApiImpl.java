package com.sicredi.desafiodigital.api.impl;

import com.sicredi.desafiodigital.api.SessaoVotacaoApi;
import com.sicredi.desafiodigital.domain.dto.SessaoVotacaoDto;
import com.sicredi.desafiodigital.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class SessaoVotacaoApiImpl implements SessaoVotacaoApi {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Override
    public ResponseEntity<List<SessaoVotacaoDto>> obterTodasAsSessoesDeVotacao() {
        var sessaoVotacaoList = sessaoVotacaoService.obterTodasAsSessoesDeVotacao();

        return new ResponseEntity(sessaoVotacaoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> iniciarSessaoDeVotacao(
            Integer codigoPauta,
            LocalDateTime dataFim
    ) {
        var codigoSessaoVotacaoCriada = sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, dataFim);

        return new ResponseEntity(codigoSessaoVotacaoCriada, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obterResultadoDaSessaoDeVotacao(Integer codigoSessaoVotacao) {

        var mensagem = sessaoVotacaoService.obterResultadoDaSessaoDeVotacao(codigoSessaoVotacao);

        return new ResponseEntity(mensagem, HttpStatus.OK);
    }
}
