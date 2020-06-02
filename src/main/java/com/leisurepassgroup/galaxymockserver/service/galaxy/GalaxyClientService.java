package com.leisurepassgroup.galaxymockserver.service.galaxy;

import org.mockserver.mock.Expectation;

import java.util.List;
import java.util.Map;

public interface GalaxyClientService {
    void createExpectation(Map<String, Object> expectation);
}
