package com.tokiomarine.desafio.controller;

import com.tokiomarine.desafio.core.queryfilter.TransferenciaFilter;
import com.tokiomarine.desafio.dto.PageableDto;
import com.tokiomarine.desafio.dto.TransferenciaRequestDto;
import com.tokiomarine.desafio.dto.TransferenciaResponseDto;
import com.tokiomarine.desafio.service.TransferenciaSevice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transferencias")
public class TransferenciaController {

    private final TransferenciaSevice transferenciaSevice;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransferenciaRequestDto request) {
        transferenciaSevice.agendarTransferencia(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PageableDto<TransferenciaResponseDto>> extrato(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size,
        @RequestParam(value = "orderBy", defaultValue = "dataTransferencia") String orderBy,
        @RequestParam(value = "direction", defaultValue = "DESC") String direction,
        TransferenciaFilter filter

    ) {

        return new ResponseEntity<>(
            transferenciaSevice.findAllByPageable(filter.toSpecification(), page, size, orderBy,
                direction),
            HttpStatus.OK);
    }
}
