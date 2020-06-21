package com.sicredi.desafiodigital.repository;

import com.sicredi.desafiodigital.domain.entity.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, String> {

    AssociadoEntity findByCpf(String cpf);
}
