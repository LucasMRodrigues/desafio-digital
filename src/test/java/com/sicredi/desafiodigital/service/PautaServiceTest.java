package com.sicredi.desafiodigital.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PautaServiceTest {

    @Autowired
    private PautaService pautaService;

    @Test
    @Rollback
    public void deveRetornarOIdDaPautaCriada() {
        var codigoPauta = pautaService.criarPauta("Pauta para validar o teste");

        Assertions.assertThat(codigoPauta).isNotNull();
    }

    @Test
    public void deveRetornarUmaPautaComNomeDeTeste() {
        var codigoPauta = pautaService.criarPauta("Teste");

        var nomePauta = pautaService.obterNomeDaPautaPeloCodigo(codigoPauta);

        Assertions.assertThat(nomePauta).isEqualTo("Teste");
    }

    @Test
    public void deveRetornarUmaListaComPeloMenos3Pautas() {
        var numeroPautaList = List.of(1, 2, 3);

        numeroPautaList.forEach(numeroPauta -> pautaService.criarPauta("Pauta " + numeroPauta));

        var pautaList = pautaService.obterTodasAsPautas();

        Assertions.assertThat(pautaList.size()).isGreaterThanOrEqualTo(3);
    }

}
