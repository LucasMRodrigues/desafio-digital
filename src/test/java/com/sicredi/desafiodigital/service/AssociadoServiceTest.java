package com.sicredi.desafiodigital.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociadoServiceTest {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Test
    public void deveRetornarOAssociadoCriado() {
        var associado = associadoService.criarAssociado("Fulano");

        Assertions.assertThat(associado).isNotNull();
    }

    @Test
    public void deveCriarERetornarOMesmoAssociado() {
        var associado = associadoService.criarAssociado("Fulano");

        var cpfFormatado = associado.getCpf().replace(".", "").replace("-", "");

        var mesmoAssociado = associadoService.buscarAssociado(cpfFormatado);

        Assertions.assertThat(associado.getCpf()).isEqualTo(mesmoAssociado.getCpf());
        Assertions.assertThat(associado.getNome()).isEqualTo(mesmoAssociado.getNome());
    }

    @Test
    public void deveRetornarMensagemQueOCpfInformadoNaoEhValid() throws IOException {
        var mensagem = associadoService.persistirVotoDoAssociadoNaSessao("123456789", 1, "sim");

        Assertions.assertThat(mensagem).isEqualTo("O cpf informado nao eh valido!");
    }

    @Test
    public void deveRetornarMensagemQueOValorDoVotoNaoEhValido() throws IOException {
        var associado = associadoService.criarAssociado("Fulano");

        var cpfFormatado = associado.getCpf().replace(".", "").replace("-", "");

        var mensagem = associadoService.persistirVotoDoAssociadoNaSessao(cpfFormatado, 1, "Valor invalido");

        Assertions.assertThat(mensagem).isEqualTo("Valor invalido para o voto!");
    }

    @Test
    public void deveRetornarMensagemQueNenhumaSessaoFoiEncontrada() throws IOException {
        var associado = associadoService.criarAssociado("Fulano");

        var cpfFormatado = associado.getCpf().replace(".", "").replace("-", "");

        var mensagem = associadoService.persistirVotoDoAssociadoNaSessao(cpfFormatado, 1, "sim");

        Assertions.assertThat(mensagem).isEqualTo("Nenhuma sessao de votacao encontrada para o codigo 1");
    }

    @Test
    public void deveRetornarMensagemQueASessaoJaEstaEncerrada() throws IOException, InterruptedException {
        var associado = associadoService.criarAssociado("Fulano");

        var cpfFormatado = associado.getCpf().replace(".", "").replace("-", "");

        var codigoPauta = pautaService.criarPauta("Teste");

        sessaoVotacaoService.iniciarSessaoVotacao(codigoPauta, LocalDateTime.now().plusSeconds(1));

        var sessaoVotacao = sessaoVotacaoService.obterTodasAsSessoesDeVotacao().get(0);

        Thread.sleep(2000);

        var mensagem = associadoService.persistirVotoDoAssociadoNaSessao(cpfFormatado, sessaoVotacao.getCodigo(), "sim");

        Assertions.assertThat(mensagem).isEqualTo("Essa sessao de votacao ja esta encerrada.");
    }
}
