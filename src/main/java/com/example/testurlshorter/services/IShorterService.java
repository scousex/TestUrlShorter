package com.example.testurlshorter.services;

public interface IShorterService {
    String getShortUrl(String baseUrl);

    String getBaseUrl(String token);
}
