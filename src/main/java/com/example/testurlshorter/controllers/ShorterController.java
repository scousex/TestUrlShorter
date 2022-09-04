package com.example.testurlshorter.controllers;

import com.example.testurlshorter.exceptions.BaseUrlNotFoundException;
import com.example.testurlshorter.exceptions.DecodingException;
import com.example.testurlshorter.exceptions.EncodingException;
import com.example.testurlshorter.services.IShorterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ShorterController {

    private final IShorterService shorterService;

    @GetMapping("/{token}")
    public ResponseEntity getBaseUrl(@PathVariable("token") String token) {
        return
                ResponseEntity.ok(shorterService.getBaseUrl(token));
    }

    @PostMapping("")
    public ResponseEntity getShortUrl(@RequestParam("baseUrl") String baseUrl){
        return ResponseEntity.ok(shorterService.getShortUrl(baseUrl));
    }

    @ExceptionHandler
    public ResponseEntity handleBaseUrlNotFoundException(BaseUrlNotFoundException exception){
        return new ResponseEntity("Ссылка не найдена", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity handleDecodingException(DecodingException exception){
        return new ResponseEntity("Ссылка не распознана", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleDecodingException(EncodingException exception){
        return new ResponseEntity("Ссылка не может быть зашифрована", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
