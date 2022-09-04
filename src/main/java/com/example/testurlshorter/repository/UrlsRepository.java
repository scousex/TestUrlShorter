package com.example.testurlshorter.repository;

public interface UrlsRepository {
    public void initUrl(String token, String baseUrl);
    public String getUrl(String token);
}
