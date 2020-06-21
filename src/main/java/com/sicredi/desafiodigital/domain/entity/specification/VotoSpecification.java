package com.sicredi.desafiodigital.domain.entity.specification;

import com.sicredi.desafiodigital.domain.entity.VotoEntity;
import org.springframework.data.jpa.domain.Specification;

public class VotoSpecification {

    public static final Specification<VotoEntity> byCodigoSessaoVotacao(Integer codigoSessaoVotacao) {

        return (root, query, cb) -> cb.equal(root.get("codigoSessaoVotacao"), codigoSessaoVotacao);
    }
}
