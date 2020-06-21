package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.entity.VotoEntity;
import com.sicredi.desafiodigital.domain.model.VotoModel;
import com.sicredi.desafiodigital.factory.VotoFactory;
import com.sicredi.desafiodigital.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.domain.entity.specification.VotoSpecification.byCodigoSessaoVotacao;
import static com.sicredi.desafiodigital.factory.VotoFactory.*;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    public List<VotoModel> obterVotosDaSessao(Integer codigoSessaoVotacao) {

        return votoRepository.findAll(byCodigoSessaoVotacao(codigoSessaoVotacao))
                .stream()
                .filter(Objects::nonNull)
                .map(VotoFactory::mapToModel)
                .collect(Collectors.toList());
    }

    public VotoModel verificarSeVotoJaExiste(Integer codigoSessaoVotacao, String cpf) {
        var voto = votoRepository.findByCodigoSessaoVotacaoAndCpfAssociado(codigoSessaoVotacao, cpf);

        return Objects.nonNull(voto) ? mapToModel(voto) : null;
    }

    public void atualizarVoto(VotoModel votoJaEfetuado) {
        votoRepository.save(mapToEntity(votoJaEfetuado));
    }

    public void persistirVoto(String cpf, Integer codigoSessaoVotacao, String voto) {
        votoRepository.save(mapToEntity(cpf, codigoSessaoVotacao, voto));
    }
}
