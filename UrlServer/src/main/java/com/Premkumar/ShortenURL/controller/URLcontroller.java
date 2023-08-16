package com.Premkumar.ShortenURL.controller;

import com.Premkumar.ShortenURL.dto.CompactURlObject;
import com.Premkumar.ShortenURL.service.URLService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

@RestController
@CrossOrigin("*")
public class URLcontroller {

    @Autowired
    private URLService urlService;


    @PostMapping("/url")
    public ResponseEntity<?> convertToUrl(@NonNull @RequestBody CompactURlObject urlStr) {
        try {

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
            if(url.equals("Time Expired"))
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(url);
            }
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

