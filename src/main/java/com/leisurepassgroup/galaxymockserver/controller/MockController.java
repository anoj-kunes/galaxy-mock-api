package com.leisurepassgroup.galaxymockserver.controller;

import com.leisurepassgroup.galaxymockserver.model.request.ExpectationRequest;
import com.leisurepassgroup.galaxymockserver.service.galaxy.GalaxyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/mock", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MockController {
    private final GalaxyService galaxyService;

    @PostMapping
    public Map<String, Boolean> createExpectation(@RequestBody List<Map<String, Object>> expectations) {
        galaxyService.createExpectations(expectations);
        return Map.of("status", true);
    }
}
