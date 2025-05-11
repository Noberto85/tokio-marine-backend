package com.tokiomarine.desafio.core.queryfilter;

import static com.tokiomarine.desafio.core.TransferenciaSpec.contDestinoLike;
import static com.tokiomarine.desafio.core.TransferenciaSpec.contOrigemLike;

import com.tokiomarine.desafio.model.Transferencia;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class TransferenciaFilter {

    private String contaOrigem;

    private String contaDestino;

    public Specification<Transferencia> toSpecification() {
        return contOrigemLike(contaOrigem).or(contDestinoLike(contaDestino));
    }
}
