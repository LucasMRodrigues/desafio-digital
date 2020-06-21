package com.sicredi.desafiodigital.repository;

import com.sicredi.desafiodigital.domain.entity.SessaoVotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacaoEntity, Integer> {

    SessaoVotacaoEntity findByCodigo(Integer codigoSessaoVotacao);
}
