package com.tokiomarine.desafio.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Builder
@Getter
@JsonInclude(Include.NON_NULL)
public class MessageException {
    private final String message;
    private HttpStatusCode status;

}
