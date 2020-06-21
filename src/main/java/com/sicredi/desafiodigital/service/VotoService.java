package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.model.VotoModel;
import com.sicredi.desafiodigital.factory.VotoFactory;
import com.sicredi.desafiodigital.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.domain.entity.specification.VotoSpecification.byCodigoSessaoVotacao;
import static com.sicredi.desafiodigital.factory.VotoFactory.*;
import static com.sicredi.desafiodigital.util.CpfHandler.*;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    AssociadoService associadoService;

    public String persistirVotoDoAssociadoNaSessao(String cpf, Integer codigoSessaoVotacao, String voto) {

        if (!voto.toLowerCase().contains("sim") && !voto.toLowerCase().contains("nao") && !voto.toLowerCase().contains("não")) {
            return "Valor invalido para o voto!";
        }

        var sessaoVotacao = sessaoVotacaoService.obterSessaoDeVotacaoPeloCodigo(codigoSessaoVotacao);

        if (Objects.isNull(sessaoVotacao)) {
            return "Nenhuma sessao de votacao encontrada para o codigo " + codigoSessaoVotacao;
        }

        if (LocalDateTime.now().isBefore(sessaoVotacao.getDataInicio())) {
            return "Essa sessao de votacao ainda nao esta aberta.";
        }

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataFim())) {
            return "Essa sessao de votacao ja esta encerrada.";
        }

        var associado = associadoService.buscarAssociado(cpf);

        if (Objects.isNull(associado)) {
            return "Nenhum associado encontrado para o CPF " + formatarCpf(cpf);
        }

        var votoJaEfetuado = votoRepository.findByCodigoSessaoVotacaoAndCpfAssociado(codigoSessaoVotacao, associado.getCpf());

        if (Objects.nonNull(votoJaEfetuado)) {
            votoJaEfetuado.setValor(voto);
            votoRepository.save(votoJaEfetuado);
        } else {
            votoRepository.save(mapToEntity(associado.getCpf(), codigoSessaoVotacao, voto));
        }

        return "Voto salvo com sucesso!";
    }

    public List<VotoModel> obterVotosDaSessao(Integer codigoSessaoVotacao) {

        return votoRepository.findAll(byCodigoSessaoVotacao(codigoSessaoVotacao))
                .stream()
                .filter(Objects::nonNull)
                .map(VotoFactory::mapToModel)
                .collect(Collectors.toList());
    }
}
