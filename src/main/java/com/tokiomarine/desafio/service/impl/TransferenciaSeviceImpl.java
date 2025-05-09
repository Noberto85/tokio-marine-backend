package com.tokiomarine.desafio.service.impl;

import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.model.Transferencia;
import com.tokiomarine.desafio.repository.TransferenciaRepository;
import com.tokiomarine.desafio.service.TransferenciaSevice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferenciaSeviceImpl implements TransferenciaSevice {

    private final ModelMapper mapper;
    private final TransferenciaRepository repository;

    @Override
    public Transferencia agendarTransferencia(final TransferenciaRequestDto transferenciaDto) {
        long dias = ChronoUnit.DAYS.between(transferenciaDto.getDataAgendamento(),
            LocalDate.now());

        BigDecimal taxa = calcularTaxa(dias, transferenciaDto.getValor());
        if (taxa == null) {
            throw new IllegalArgumentException(
                "Nenhuma taxa aplicável. Transferência não permitida.");
        }
        final Transferencia transferencia = mapper.map(transferenciaDto, Transferencia.class);
        transferencia.setTaxa(taxa);
        return repository.save(transferencia);
    }

    private BigDecimal calcularTaxa(final long dias, final BigDecimal valor) {
        if (dias == 0) {
            return BigDecimal.valueOf(3).add(valor.multiply(BigDecimal.valueOf(0.025)));
        }
        if (dias >= 1 && dias <= 10) {
            return BigDecimal.valueOf(12);
        }
        if (dias >= 11 && dias <= 20) {
            return valor.multiply(BigDecimal.valueOf(0.082));
        }
        if (dias >= 21 && dias <= 30) {
            return valor.multiply(BigDecimal.valueOf(0.069));
        }
        if (dias >= 31 && dias <= 40) {
            return valor.multiply(BigDecimal.valueOf(0.047));
        }
        if (dias >= 41 && dias <= 50) {
            return valor.multiply(BigDecimal.valueOf(0.017));
        }
        return null;
    }

}
