package com.example.testurlshorter.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlsInMemoryRepository implements UrlsRepository {
    private Map<String, String> repository = new HashMap<>();

    @Override
    public void initUrl(String token, String baseUrl) {
        repository.put(token, baseUrl);
    }

    @Override
    public String getUrl(String token) {
        return repository.get(token);
    }
}
