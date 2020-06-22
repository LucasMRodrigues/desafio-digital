package com.sicredi.desafiodigital.api;

import com.sicredi.desafiodigital.domain.dto.SessaoVotacaoDto;
import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(value = "sessao-votacoes-api",
        produces = APPLICATION_JSON_UTF8_VALUE,
        tags = "Sessão de Votações API",
        description = "Operações relacionadas a Entidade de Sessão de Votações.")
@RequestMapping(path = "/sessao-votacoes")
public interface SessaoVotacaoApi {

    @GetMapping()
    ResponseEntity<List<SessaoVotacaoDto>> obterTodasAsSessoesDeVotacao();

    @PostMapping("/new")
    ResponseEntity<Integer> iniciarSessaoDeVotacao(
            @RequestParam("codigoPauta") Integer codigoPauta,
            @RequestParam(value = "dataFim", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim);

    @GetMapping("{codigoSessaoVotacao}/resultado")
    ResponseEntity<String> obterResultadoDaSessaoDeVotacao(
            @PathVariable("codigoSessaoVotacao") Integer codigoSessaoVotacao);
}
