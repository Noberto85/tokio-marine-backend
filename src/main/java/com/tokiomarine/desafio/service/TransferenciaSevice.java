package com.tokiomarine.desafio.service;

import com.tokiomarine.desafio.dto.PageableDto;
import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.dto.TransferenciaResponseDto;
import com.tokiomarine.desafio.model.Transferencia;
import org.springframework.data.jpa.domain.Specification;

public interface TransferenciaSevice {

    void agendarTransferencia(TransferenciaRequestDto transferenciaDto);

    PageableDto<TransferenciaResponseDto> findAllByPageable(
        final Specification<Transferencia> spec,
        final Integer page, final Integer size, final String orderBy, final String direction);
}
