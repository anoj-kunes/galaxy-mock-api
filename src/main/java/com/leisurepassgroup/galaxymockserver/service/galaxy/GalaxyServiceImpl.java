package com.leisurepassgroup.galaxymockserver.service.galaxy;

import lombok.AllArgsConstructor;
import org.mockserver.mock.Expectation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GalaxyServiceImpl implements GalaxyService {
    private final GalaxyClientService galaxyClientService;

    public void createExpectations(List<Map<String, Object>> expectations) {
        for (var expectation: expectations) {
            galaxyClientService.createExpectation(expectation);
        }
    }
}
