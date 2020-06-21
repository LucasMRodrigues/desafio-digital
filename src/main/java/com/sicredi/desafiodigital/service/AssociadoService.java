package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import com.sicredi.desafiodigital.domain.model.AssociadoModel;
import com.sicredi.desafiodigital.factory.AssociadoFactory;
import com.sicredi.desafiodigital.repository.AssociadoRepository;
import com.sicredi.desafiodigital.util.CpfHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToModel;
import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToDto;
import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToEntity;
import static com.sicredi.desafiodigital.util.CpfHandler.*;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private VotoService votoService;

    public AssociadoModel buscarAssociado(String cpf) {

        var associado = associadoRepository.findByCpf(formatarCpf(cpf));

        return Objects.nonNull(associado) ? mapToModel(associado) : null;
    }

    public AssociadoDto salvarAssociado(String nome) {
        var cpf = gerarCPF();

        return mapToDto(associadoRepository.save(mapToEntity(cpf, nome)));
    }

    public List<AssociadoDto> buscarTodosOsAssociados() {
        return associadoRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(AssociadoFactory::mapToDto)
                .collect(Collectors.toList());
    }

    public String persistirVotoDoAssociadoNaSessao(String cpf, Integer codigoSessaoVotacao, String voto) throws IOException {

        var cpfInvalido = validarCpf(cpf) != 200;

        if (cpfInvalido) {
            return "O cpf informado nao eh valido!";
        }

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

        var associado = buscarAssociado(cpf);

        if (Objects.isNull(associado)) {
            return "Nenhum associado encontrado para o CPF " + formatarCpf(cpf);
        }

        var votoJaEfetuado = votoService.verificarSeVotoJaExiste(codigoSessaoVotacao, associado.getCpf());

        if (Objects.nonNull(votoJaEfetuado)) {
            votoJaEfetuado.setValor(voto);
            votoService.atualizarVoto(votoJaEfetuado);
        } else {
            votoService.persistirVoto(associado.getCpf(), codigoSessaoVotacao, voto);
        }

        return "Voto salvo com sucesso!";
    }
}
