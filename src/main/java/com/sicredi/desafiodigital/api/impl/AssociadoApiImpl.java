package com.sicredi.desafiodigital.api.impl;

import com.sicredi.desafiodigital.api.AssociadoApi;
import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import com.sicredi.desafiodigital.service.AssociadoService;
import com.sicredi.desafiodigital.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssociadoApiImpl implements AssociadoApi {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private VotoService votoService;

    @Override
    public ResponseEntity<AssociadoDto> criarAssociado(String nome) {
        var associado = associadoService.salvarAssociado(nome);

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
    public ResponseEntity<String> votar(String cpf, Integer codigoSessaoVotacao, String voto) {

        var mensagem = votoService.persistirVotoDoAssociadoNaSessao(cpf, codigoSessaoVotacao, voto);

        return new ResponseEntity(mensagem, HttpStatus.OK);
    }
}
