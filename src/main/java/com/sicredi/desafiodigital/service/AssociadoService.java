package com.sicredi.desafiodigital.service;

import com.sicredi.desafiodigital.domain.dto.AssociadoDto;
import com.sicredi.desafiodigital.domain.model.AssociadoModel;
import com.sicredi.desafiodigital.factory.AssociadoFactory;
import com.sicredi.desafiodigital.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sicredi.desafiodigital.util.CpfHandler.formatarCpf;
import static com.sicredi.desafiodigital.util.CpfHandler.gerarCPF;
import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToModel;
import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToDto;
import static com.sicredi.desafiodigital.factory.AssociadoFactory.mapToEntity;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

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
}
