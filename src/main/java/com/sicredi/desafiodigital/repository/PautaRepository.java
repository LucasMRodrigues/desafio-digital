package com.sicredi.desafiodigital.repository;

import com.sicredi.desafiodigital.domain.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Integer> {

    PautaEntity findByCodigo(Integer codigoPauta);
}
