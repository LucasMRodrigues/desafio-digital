package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.dto.PautaDto;
import com.sicredi.desafiodigital.domain.model.PautaModel;
import com.sicredi.desafiodigital.factory.PautaFactory;
import com.sicredi.desafiodigital.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.factory.PautaFactory.*;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public PautaModel obterPautaPeloCodigo(Integer codigoPauta) {
        var pauta = pautaRepository.findByCodigo(codigoPauta);
        return Objects.nonNull(pauta) ? mapToModel(pauta) : null;
    }

    public String obterNomeDaPautaPeloCodigo(Integer codigoPauta) {
        var pauta = pautaRepository.findByCodigo(codigoPauta);
        return Objects.nonNull(pauta) ? pauta.getNome() : null;
    }

    public Integer criarPauta(String nomePauta) {
        return pautaRepository.save(mapToEntity(nomePauta)).getCodigo();
    }

    public List<PautaDto> obterTodasAsPautas() {
        return pautaRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(PautaFactory::mapToDto)
                .collect(Collectors.toList());
    }
}
