package com.sicredi.desafiodigital.api;

import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(value = "associados-api",
     produces = APPLICATION_JSON_UTF8_VALUE,
     tags = "Associados API",
     description = "Operações relacionadas a Entidade de Associados.")
@RequestMapping(path = "/associados")
public interface AssociadoApi {

    @GetMapping()
    ResponseEntity<Integer> buscarTodosOsAssociados();

    @GetMapping("/{cpf}")
    ResponseEntity<AssociadoDto> buscarAssociado(@PathVariable("cpf") String cpf);

    @PostMapping("/new")
    ResponseEntity<AssociadoDto> criarAssociado(@RequestParam("nome") String nome);

    @PostMapping("/votar")
    ResponseEntity<String> votar(@RequestParam("cpf") String cpf,
                                 @RequestParam("codigoSessaoVotacao") Integer codigoSessaoVotacao,
                                 @RequestParam("voto") String voto) throws IOException;
}
