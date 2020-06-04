package com.leisurepassgroup.galaxymockserver.service.galaxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leisurepassgroup.galaxymockserver.model.request.ExpectationRequest;
import com.leisurepassgroup.galaxymockserver.model.request.TicketActivationRequest;
import com.leisurepassgroup.galaxymockserver.model.response.TicketActivationErrorResponse;
import com.leisurepassgroup.galaxymockserver.model.response.TicketActivationResponse;
import lombok.extern.slf4j.Slf4j;
import org.mockserver.mock.Expectation;
import org.mockserver.model.Header;
import org.mockserver.model.MediaType;
import org.mockserver.netty.MockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

import static java.lang.String.format;
import static org.mockserver.matchers.Times.once;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.HttpMethod.PUT;

@Slf4j
@Service
public class GalaxyClientServiceImpl implements GalaxyClientService {

    private static final String EXPECTATION_URL = "/mockserver/expectation";
    private static final String RESET_URL = "/mockserver/reset";
    private static final String VERIFICATION_URL = "/mockserver/verification";

    private final RestTemplate restTemplate;
    private final String expectationUrl;
    private final String resetUrl;
    private final String verificationUrl;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String PRODUCT_CODE = "PC01";
    public static final String SUPPLIER_NAME = "LPG_INTEGRATION_TEST";
    public static final String CORRELATION_ID = UUID.randomUUID().toString();


    public GalaxyClientServiceImpl(@Value("${mock-service.url}") String url, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.expectationUrl = url + EXPECTATION_URL;
        this.resetUrl = url + RESET_URL;
        this.verificationUrl = url + VERIFICATION_URL;
        LOG.info("Galaxy service initialised with {}", url);
    }

    public void createExpectation(Map<String, Object> expectation) {
        try {
            Expectation expectation1 = new Expectation(
                    request()
                            .withHeader(new Header("x-api-key", "abcdef12345"))
                    .withHeader(new Header("Content-Type", "application/json"))
                    .withMethod("POST")
                    .withPath("/api/hello/world/101")
                    .withBody(objectMapper.writeValueAsString(createDefaultRequest())))
                    .thenRespond(response()
                            .withHeader(new Header("Content-Type", "application/json")).withHeader(new Header("x-api-key", "abcdef12345"))
                            .withStatusCode(201).withBody(objectMapper.writeValueAsString(createDefaultResponse(101, null))));

            var object = expectation1.toString();

            LOG.info("values = {}", object);

            var entity = new HttpEntity<>(object, new HttpHeaders());
            restTemplate.exchange(expectationUrl, PUT, entity, Void.class);
            LOG.info("SUCCESS: Create Expectations for #expectation - {}", expectation1);
        } catch (Exception ex) {
            LOG.info("FAILED: Create Expectations for #expectation - {}", expectation, ex);
        }
    }

    public static TicketActivationResponse createDefaultResponse(Integer ticketNumber, TicketActivationErrorResponse errors) {
        return new TicketActivationResponse(
                ticketNumber.toString(),
                PRODUCT_CODE,
                SUPPLIER_NAME,
                1,
                CORRELATION_ID,
                errors
        );
    }

    public static TicketActivationRequest createDefaultRequest() {
        return new TicketActivationRequest(PRODUCT_CODE);
    }

    public void reset(Map<String, Object> reset) {
        try {
            var entity = new HttpEntity<>(reset, new HttpHeaders());
            restTemplate.exchange(resetUrl, PUT, entity, Void.class);
            LOG.info("SUCCESS: reset for #reset - {}", reset);
        } catch (Exception ex) {
            LOG.info("FAILED: reset for #reset - {}", reset, ex);
        }
    }

    public void verification(Map<String, Object> verification) {
        try {
            var entity = new HttpEntity<>(verification, new HttpHeaders());
            restTemplate.exchange(resetUrl, PUT, entity, Void.class);
            LOG.info("SUCCESS: verification for #verification - {}", verification);
        } catch (Exception ex) {
            LOG.info("FAILED: verification for #verification - {}", verification, ex);
        }
    }
}
