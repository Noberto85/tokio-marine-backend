package com.tokiomarine.desafio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransferenciaRequestDto {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private LocalDate dataAgendamento;

}
