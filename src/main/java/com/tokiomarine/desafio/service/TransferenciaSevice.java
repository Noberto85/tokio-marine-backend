package com.tokiomarine.desafio.service;

import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.model.Transferencia;

public interface TransferenciaSevice {
    Transferencia agendarTransferencia(TransferenciaRequestDto transferenciaDto);
}
