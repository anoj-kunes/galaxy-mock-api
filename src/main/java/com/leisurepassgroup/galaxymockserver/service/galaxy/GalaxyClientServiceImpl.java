package com.leisurepassgroup.galaxymockserver.service.galaxy;

import lombok.extern.slf4j.Slf4j;
import org.mockserver.mock.Expectation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpMethod.PUT;

@Slf4j
@Service
public class GalaxyClientServiceImpl implements GalaxyClientService {

    private static final String EXPECTATION_URL = "/mockserver/expectation";
    private static final String RESET_URL = "/mockserver/expectation";

    private final RestTemplate restTemplate;
    private final String createExpectationUrl;

    public GalaxyClientServiceImpl(@Value("${mock-service.url}") String url, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.createExpectationUrl = url + EXPECTATION_URL;
        LOG.info("Galaxy service initialised with {}", url);
    }

    public void createExpectation(Map<String, Object> expectation) {
        try {
            var entity = new HttpEntity<>(expectation, new HttpHeaders());
            restTemplate.exchange(createExpectationUrl, PUT, entity, Void.class);
            LOG.info("SUCCESS: Create Expectations for #expectation - {}", expectation);
        } catch (Exception ex) {
            LOG.info("FAILED: Create Expectations for #expectation - {}", expectation, ex);
        }
    }
}
