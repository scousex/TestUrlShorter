package com.example.testurlshorter.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlsInMemoryRepositoryImpl {
    private Map<String, String> repository = new HashMap<>();

    public void initUrl(String token, String baseUrl) {
        repository.put(token, baseUrl);
    }

    public String getUrl(String token){
        repository.get(token);
    }
}
