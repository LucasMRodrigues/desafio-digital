package com.sicredi.desafiodigital.repository;

import com.sicredi.desafiodigital.domain.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long>,
        JpaSpecificationExecutor<VotoEntity> {

    VotoEntity findByCodigoSessaoVotacaoAndCpfAssociado(Integer codigoSessaoVotacao, String cpfFormatado);
}
