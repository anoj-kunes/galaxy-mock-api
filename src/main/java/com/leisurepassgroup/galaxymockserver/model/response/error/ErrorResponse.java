package com.leisurepassgroup.galaxymockserver.model.response.error;

import lombok.Value;

@Value
public class ErrorResponse {
    Integer httpStatusCode;
    Integer errorCode;
    String message;
    String correlationId;
}
