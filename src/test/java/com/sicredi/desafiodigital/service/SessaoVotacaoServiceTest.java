package com.sicredi.desafiodigital.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SessaoVotacaoServiceTest {

    @MockBean
    private SessaoVotacaoService sessaoVotacaoService;

    @Test
    public void deveRetornarMensagemDeSessaoNaoEncontrada() {
        var mensagem = sessaoVotacaoService.obterResultadoDaSessaoDeVotacao(999999);

        Assertions.assertThat(mensagem).contains("Nenhuma sessao encontrada");
    }
}
