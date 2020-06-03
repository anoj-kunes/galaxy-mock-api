package com.leisurepassgroup.galaxymockserver.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectationRequest extends Expectation {

    public ExpectationRequest() {
        super(HttpRequest.request());
    }
}
