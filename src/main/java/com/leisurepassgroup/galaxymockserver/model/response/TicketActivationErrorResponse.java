package com.leisurepassgroup.galaxymockserver.model.response;

import lombok.Value;

@Value
public class TicketActivationErrorResponse {
    String errorCode;
    String errorText;
}
