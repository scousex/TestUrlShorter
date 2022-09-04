package com.example.testurlshorter.services;

import com.example.testurlshorter.repository.UrlsRepository;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShorterService implements IShorterService {

    private final UrlsRepository urlsInMemoryRepository;
    private final Hashids hashids;

    @Override
    public String getShortUrl(String baseUrl) {
        long longVal = baseUrl.hashCode();
        String token = hashids.encode(longVal);
        urlsInMemoryRepository.initUrl(token, baseUrl);
        return token;
    }

    @Override
    public String getBaseUrl(String token) {
        return urlsInMemoryRepository.getUrl(token);
    }
}
