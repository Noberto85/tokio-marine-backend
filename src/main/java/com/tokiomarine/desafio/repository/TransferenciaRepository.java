package com.tokiomarine.desafio.repository;

import com.tokiomarine.desafio.model.Transferencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>,
    JpaSpecificationExecutor<Transferencia> {

    List<Transferencia> findByContaOrigem(String contaOrigem);
}

