package com.tokiomarine.desafio.service.impl;

import static com.tokiomarine.desafio.utils.CalculaUtils.calcularTaxa;

import com.tokiomarine.desafio.dto.PageableDto;
import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.dto.TransferenciaResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public void agendarTransferencia(final TransferenciaRequestDto transferenciaDto) {
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
        repository.save(transferencia);
    }

    @Override
    public PageableDto<TransferenciaResponseDto> findAllByPageable(
        final Specification<Transferencia> spec,
        final Integer page, final Integer size, final String orderBy, final String direction) {
        final var pageRequest = PageRequest.of(page, size,
            Sort.by(Sort.Direction.valueOf(direction), orderBy));
        Page<TransferenciaResponseDto> maplis = repository.findAll(spec, pageRequest)
            .map(f -> mapper.map(f, TransferenciaResponseDto.class));

        return new PageableDto<>(maplis);
    }

}
