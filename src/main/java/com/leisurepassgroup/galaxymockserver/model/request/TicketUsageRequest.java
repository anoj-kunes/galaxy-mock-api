package com.leisurepassgroup.galaxymockserver.model.request;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class TicketUsageRequest {
    String productCode;
    Integer quantity;
    Integer status;
    ZonedDateTime useTime;
    String operationId;
}
