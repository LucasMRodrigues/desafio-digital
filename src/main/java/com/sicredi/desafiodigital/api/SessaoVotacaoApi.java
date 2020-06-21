package com.sicredi.desafiodigital.api;

import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(value = "sessao-votacoes-api",
        produces = APPLICATION_JSON_UTF8_VALUE,
        tags = "Sessão de Votações API",
        description = "Operações relacionadas a Entidade de Sessão de Votações.")
@RequestMapping(path = "/sessao-votacoes")
public interface SessaoVotacaoApi {

    @PostMapping("/new")
    ResponseEntity<Integer> criarSessaoDeVotacao(
            @RequestParam("codigoPauta") Integer codigoPauta,
            @RequestParam(value = "dataInicio", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(value = "dataFim", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim);

    @GetMapping("{codigoSessaoVotacao}/resultado")
    ResponseEntity<String> obterResultadoDaSessaoDeVotacao(
            @PathVariable("codigoSessaoVotacao") Integer codigoSessaoVotacao);
}
