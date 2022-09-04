package com.example.testurlshorter.services;

import com.example.testurlshorter.exceptions.DecodingException;
import com.example.testurlshorter.repository.UrlsRepository;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ShorterService implements IShorterService {

    private final UrlsRepository urlsInMemoryRepository;
    private final Hashids hashids;
    private static final String regexp = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
    private static final Pattern pattern = Pattern.compile(regexp);

    @Override
    public String getShortUrl(String baseUrl) {
        validateBaseUrl(baseUrl);
        long longVal = baseUrl.hashCode();
        String token = hashids.encode(longVal);
        urlsInMemoryRepository.initUrl(token, baseUrl);
        return token;
    }

    @Override
    public String getBaseUrl(String token) {
        validateToken(token);
        return urlsInMemoryRepository.getUrl(token);
    }

    private void validateBaseUrl(String baseUrl) {
        if (baseUrl.isBlank()) {
            throw new DecodingException("Ссылка пустая.");
        }

        if(!pattern.matcher(baseUrl).matches()) {
            throw new DecodingException("Некорректный формат ссылки");
        }
    }

    private void validateToken(String token) {
        if (token.isBlank()) {
            throw new DecodingException("Токен пустой.");
        }

        if (token.length() < 4) {
            throw new DecodingException("Токен слишком короткий.");
        }
    }
}
