package com.sicredi.desafiodigital.api.impl;

import com.sicredi.desafiodigital.api.AssociadoApi;
import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import com.sicredi.desafiodigital.service.AssociadoService;
import com.sicredi.desafiodigital.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AssociadoApiImpl implements AssociadoApi {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Override
    public ResponseEntity<AssociadoDto> criarAssociado(String nome) {
        var associado = associadoService.criarAssociado(nome);

        return new ResponseEntity(associado, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> buscarTodosOsAssociados() {
        var associadoList = associadoService.buscarTodosOsAssociados();

        return new ResponseEntity(associadoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AssociadoDto> buscarAssociado(String cpf) {
        var associado = associadoService.buscarAssociado(cpf);

        return new ResponseEntity(associado, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> votar(String cpf, Integer codigoSessaoVotacao, String voto) throws IOException {

        var mensagem = associadoService.persistirVotoDoAssociadoNaSessao(cpf, codigoSessaoVotacao, voto);

        return new ResponseEntity(mensagem, HttpStatus.OK);
    }
}
