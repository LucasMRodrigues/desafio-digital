package com.sicredi.desafiodigital.util;

import java.util.Random;

public class CpfHandler {

    public static final String gerarCPF() {

        int digito1, digito2, resto;
        String nDigResult;
        String numerosContatenados;
        String numeroGerado;

        Random numeroAleatorio = new Random();

        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);

        int soma = n9*2 + n8*3 + n7*4 + n6*5 + n5*6 + n4*7 + n3*8 + n2*9 + n1*10;

        int valor = (soma / 11) * 11;

        digito1 = soma-valor;

        resto = (digito1 % 11);

        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11-resto;
        }

        int soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 *5  + n6 *6  + n5 * 7 + n4 *8  + n3 * 9 + n2 * 10 + n1 * 11;

        int valor2 = (soma2 / 11) * 11;

        digito2 = soma2-valor2;

        resto = (digito2 % 11);

        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11-resto;
        }

        numerosContatenados = n1 + String.valueOf(n2) + (n3) + "." + n4 + n5 + n6 + "." + n7 + n8 + n9 + "-";

        nDigResult = digito1 + String.valueOf(digito2);

        numeroGerado = numerosContatenados+nDigResult;

        System.out.println("CPF Gerado " + numeroGerado);

        return numeroGerado.trim();
    }

    public static final String formatarCpf(String cpf) {

        String bloco1 = cpf.substring(0, 3);
        String bloco2 = cpf.substring(3, 6);
        String bloco3 = cpf.substring(6, 9);
        String bloco4 = cpf.substring(9, 11);

        return bloco1 + "." + bloco2 + "." + bloco3 + "-" + bloco4;
    }
}
