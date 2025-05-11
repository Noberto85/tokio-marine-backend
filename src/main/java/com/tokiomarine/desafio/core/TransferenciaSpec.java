package com.tokiomarine.desafio.core;

import com.tokiomarine.desafio.model.Transferencia;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

public class TransferenciaSpec {

    private static final String CONTA_ORIGEM = "contaOrigem";

    private static final String CONTA_DESTINO = "contaDestino";

    public static Specification<Transferencia> contOrigemLike(String conta) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(conta)) {
                return null;
            }
            return builder.like(builder.lower(root.get(CONTA_ORIGEM)),
                "%" + conta.toLowerCase() + "%");
        };
    }

    public static Specification<Transferencia> contDestinoLike(String conta) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(conta)) {
                return null;
            }
            return builder.like(builder.lower(root.get(CONTA_DESTINO)),
                "%" + conta.toLowerCase() + "%");
        };
    }
}
