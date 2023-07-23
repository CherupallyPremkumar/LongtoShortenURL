package com.Premkumar.ShortenURL.controller;

import com.Premkumar.ShortenURL.dto.CompactURlObject;
import com.Premkumar.ShortenURL.service.URLService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin("*")
public class URLcontroller {

    @Autowired
    private URLService urlService;


    @PostMapping("/url")
    public ResponseEntity<?> convertToUrl(@NonNull @RequestBody CompactURlObject urlStr) {
        try {
            System.out.println(urlStr.toString());
            CompactURlObject result = urlService.getShortenUrl(urlStr);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle any exception that might occur during URL conversion
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while converting URL.");
        }
    }


    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<String> getAndRedirect(@PathVariable String shortUrl) {
        try {
            var url = urlService.checkshortestURL(shortUrl);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(url))
                    .build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("URL not found.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while redirecting to the original URL.");
        }
    }
}

