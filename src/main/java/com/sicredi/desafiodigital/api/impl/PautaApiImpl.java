package com.sicredi.desafiodigital.api.impl;

import com.sicredi.desafiodigital.api.PautaApi;
import com.sicredi.desafiodigital.domain.dto.PautaDto;
import com.sicredi.desafiodigital.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PautaApiImpl implements PautaApi {

    @Autowired
    PautaService pautaService;

    @Override
    public ResponseEntity<List<PautaDto>> obterTodasAsPautas() {
        var pautaList = pautaService.obterTodasAsPautas();

        return new ResponseEntity(pautaList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> criarPauta(String nomePauta) {

        return new ResponseEntity(pautaService.criarPauta(nomePauta), HttpStatus.OK);
    }
}
