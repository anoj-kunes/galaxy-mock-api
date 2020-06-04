package com.leisurepassgroup.galaxymockserver.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpClassCallback;
import org.mockserver.model.HttpError;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpObjectCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpTemplate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectationRequest {
    @JsonIgnoreProperties("headerList")
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private HttpTemplate httpResponseTemplate;
    private HttpForward httpForward;
    private HttpTemplate httpForwardTemplate;
    private HttpClassCallback httpClassCallback;
    private HttpObjectCallback httpObjectCallback;
    private HttpError httpError;
    private Times times;
    private TimeToLive timeToLive;
}
