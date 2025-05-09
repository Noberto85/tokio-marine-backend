package com.tokiomarine.desafio.service.impl;

import static com.tokiomarine.desafio.utils.CalculaUtils.calcularTaxa;

import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.exception.TransacaoException;
import com.tokiomarine.desafio.model.Transferencia;
import com.tokiomarine.desafio.repository.TransferenciaRepository;
import com.tokiomarine.desafio.service.TransferenciaSevice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TransferenciaSeviceImpl implements TransferenciaSevice {

    private final ModelMapper mapper;
    private final TransferenciaRepository repository;

    @Override
    @Transactional
    public Transferencia agendarTransferencia(final TransferenciaRequestDto transferenciaDto) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), transferenciaDto.getDataAgendamento());

        if (dias < 0) {
            throw new TransacaoException(
                "A data de agendamento não pode ser anterior à data atual.");
        }

        BigDecimal taxa = calcularTaxa(dias, transferenciaDto.getValor());
        if (taxa == null) {
            throw new TransacaoException("Nenhuma taxa aplicável. Transferência não permitida.");
        }

        log.info("Dias entre a data de agendamento e a data atual: {} com taxa de: {}", dias, taxa);
        final Transferencia transferencia = mapper.map(transferenciaDto, Transferencia.class);
        transferencia.setTaxa(taxa);
        return repository.save(transferencia);
    }

}
