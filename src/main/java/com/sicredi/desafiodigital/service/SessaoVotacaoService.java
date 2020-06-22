package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.dto.SessaoVotacaoDto;
import com.sicredi.desafiodigital.domain.model.SessaoVotacaoModel;
import com.sicredi.desafiodigital.factory.SessaoVotacaoFactory;
import com.sicredi.desafiodigital.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.factory.SessaoVotacaoFactory.mapToEntity;
import static com.sicredi.desafiodigital.factory.SessaoVotacaoFactory.mapToModel;

@Slf4j
@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    PautaService pautaService;

    @Autowired
    VotoService votoService;

    public SessaoVotacaoModel obterSessaoDeVotacaoPeloCodigo(Integer codigoSessaoVotacao) {

        var sessaoVotacao = sessaoVotacaoRepository.findByCodigo(codigoSessaoVotacao);

        if (Objects.nonNull(sessaoVotacao)) {
            return mapToModel(sessaoVotacao);
        }

        return null;
    }

    public String obterResultadoDaSessaoDeVotacao(Integer codigoSessaoVotacao) {
        var sessaoVotacao = obterSessaoDeVotacaoPeloCodigo(codigoSessaoVotacao);

        if (Objects.isNull(sessaoVotacao)) {
            return "Nenhuma sessao encontrada com o codigo " + codigoSessaoVotacao;
        }

        var nomePauta = pautaService.obterNomeDaPautaPeloCodigo(sessaoVotacao.getCodigoPauta());

        var votoList = votoService.obterVotosDaSessao(codigoSessaoVotacao);

        var votosSim = new AtomicInteger();
        var votosNao = new AtomicInteger();

        votosSim.set(0);
        votosNao.set(0);

        votoList
                .stream()
                .filter(Objects::nonNull)
                .peek(voto -> {
                    if (voto.getValor().toLowerCase().equals("sim")) {
                        votosSim.incrementAndGet();
                    } else {
                        votosNao.incrementAndGet();
                    }
                })
                .collect(Collectors.toList());

        log.info("Votos sim: " + votosSim);
        log.info("Votos não: " + votosNao);

        var resultadoSim = votosSim.get() == 1 ? votosSim + " voto sim e " : votosSim + " votos sim e ";
        var resultadoNao = votosNao.get() == 1 ? votosNao + " voto nao. " : votosNao + " votos nao. ";

        var definicao = votosSim.get() > votosNao.get() ? "\nPauta aprovada!" : "\nPauta reprovada!";

        return "O resultado da sessao de votacao " + sessaoVotacao.getCodigo() + ", referente a pauta " +
                nomePauta + ", foi: " + resultadoSim + resultadoNao + definicao;
    }

    public String iniciarSessaoVotacao(Integer codigoPauta, LocalDateTime dataFim) {

        if (Objects.isNull(dataFim)) {
            dataFim = LocalDateTime.now().plusMinutes(1);
        }

        if (dataFim.isBefore(LocalDateTime.now())) {
            return "A data de termino nao pode ser anterior a data atual!";
        }

        var pauta = pautaService.obterPautaPeloCodigo(codigoPauta);

        if (Objects.isNull(pauta)) {
            return "Nenhuma pauta encontrada para o codigo " + codigoPauta;
        }

        sessaoVotacaoRepository.save(mapToEntity(codigoPauta, dataFim));

        return "Sessao de votacao para a pauta " + pauta.getNome() + " criada com sucesso!";
    }

    public List<SessaoVotacaoDto> obterTodasAsSessoesDeVotacao() {

        AtomicReference<String> nomePauta = new AtomicReference<>();

        return sessaoVotacaoRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .peek(sessaoVotacao -> {
                    nomePauta.set(pautaService.obterNomeDaPautaPeloCodigo(sessaoVotacao.getCodigoPauta()));
                })
                .map(sessaoVotacao -> SessaoVotacaoFactory.mapToDto(sessaoVotacao, nomePauta.get()))
                .collect(Collectors.toList());
    }
}
