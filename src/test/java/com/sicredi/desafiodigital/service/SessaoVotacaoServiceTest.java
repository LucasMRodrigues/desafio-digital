package com.sicredi.desafiodigital.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoVotacaoServiceTest {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;

    @Test
    public void deveRetornarMensagemDeSessaoNaoEncontrada() {
        var codigoSessaoVotacao = 999999;

        var mensagem = sessaoVotacaoService.obterResultadoDaSessaoDeVotacao(codigoSessaoVotacao);

        Assertions.assertThat(mensagem).contains("Nenhuma sessao encontrada com o codigo " + codigoSessaoVotacao);
    }

    @Test
    public void deveRetornarMensagemDeDataTerminoAnteriorADataAtual() {
        var codigoPauta = 999999;

        var dataFim = LocalDateTime.now().minusDays(1);

        var mensagem = sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, dataFim);

        Assertions.assertThat(mensagem).contains("A data de termino nao pode ser anterior a data atual!");
    }

    @Test
    public void deveRetornarMensagemDeNenhumaPautaEncontrada() {
        var codigoPauta = 999999;

        var dataFim = LocalDateTime.now().plusDays(1);

        var mensagem = sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, dataFim);

        Assertions.assertThat(mensagem).contains("Nenhuma pauta encontrada para o codigo " + codigoPauta);
    }

    @Test
    public void deveRetornarMensagemDeSucessoAoCriarASessaoDeVotacao() {

        var nomePauta = "Pauta de teste para criar Sessão de Votação";

        var codigoPauta = pautaService.criarPauta(nomePauta);

        var dataFim = LocalDateTime.now().plusDays(1);

        var mensagem = sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, dataFim);

        Assertions.assertThat(mensagem).contains(
                "Sessao de votacao para a pauta " + nomePauta + " criada com sucesso!");
    }

    @Test
    public void deveRetornarSessaoDeVotacaoComApenas1MinutoDeDuracao() {

        var nomePauta = "Pauta para validar duração da Sessão";

        var codigoPauta = pautaService.criarPauta(nomePauta);

        sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, null);

        var sessaoVotacaoList = sessaoVotacaoService.obterTodasAsSessoesDeVotacao();

        var dataDeEncerramentoDaSessao = sessaoVotacaoList.get(0).getDataFim();

        Assertions.assertThat(dataDeEncerramentoDaSessao).isAfter(LocalDateTime.now()).isBefore(LocalDateTime.now().plusMinutes(1));
    }

    @Test
    public void deveRetornarOResultadoDeSessaoAprovada() {

        var numeroAssociadoList = List.of(1, 2, 3);

        numeroAssociadoList.forEach(numero -> associadoService.criarAssociado("Associado " + numero));

        var associadoList = associadoService.buscarTodosOsAssociados();

        var nomePauta = "Pauta para validar resultado da Sessão";

        var codigoPauta = pautaService.criarPauta(nomePauta);

        sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, null);

        var sessaoVotacao = sessaoVotacaoService.obterTodasAsSessoesDeVotacao().get(0);

        associadoList.forEach(associado -> {
            var cpfFormatado = associado.getCpf().replace(".", "").replace("-", "");
            try {
                associadoService.persistirVotoDoAssociadoNaSessao(cpfFormatado, sessaoVotacao.getCodigo(), "sim");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        var mensagem = sessaoVotacaoService.obterResultadoDaSessaoDeVotacao(sessaoVotacao.getCodigo());

        Assertions.assertThat(mensagem).contains("Pauta aprovada!");
    }
}

