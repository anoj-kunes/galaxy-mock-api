package com.leisurepassgroup.galaxymockserver.service.galaxy;

import org.mockserver.mock.Expectation;

import java.util.List;
import java.util.Map;

public interface GalaxyService {
    void createExpectations(List<Map<String, Object>> expectations);
}
