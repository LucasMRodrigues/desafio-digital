package com.sicredi.desafiodigital.api;

import com.sicredi.desafiodigital.domain.dto.PautaDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(value = "pautas-api",
        produces = APPLICATION_JSON_UTF8_VALUE,
        tags = "Pautas API",
        description = "Operações relacionadas a Entidade de Pautas.")
@RequestMapping(path = "/pautas")
public interface PautaApi {

    @GetMapping()
    ResponseEntity<List<PautaDto>> obterTodasAsPautas();

    @PostMapping("/new")
    ResponseEntity<Integer> criarPauta(@RequestParam("nomePauta") String nomePauta);
}
