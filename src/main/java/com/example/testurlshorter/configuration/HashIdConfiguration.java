package com.example.testurlshorter.configuration;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashIdConfiguration {
    private static final String hashSalt = "TEST_FOR_NETOLOGY";

    @Bean
    public Hashids hashids(){
        return new Hashids(hashSalt);
    }
}
