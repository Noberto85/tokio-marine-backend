package com.tokiomarine.desafio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransferenciaResponseDto {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    private LocalDate dataAgendamento;
    private BigDecimal taxa;

}
