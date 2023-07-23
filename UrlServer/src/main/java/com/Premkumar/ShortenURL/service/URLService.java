package com.Premkumar.ShortenURL.service;

import com.Premkumar.ShortenURL.builder.Converstion;
import com.Premkumar.ShortenURL.dto.CompactURlObject;
import com.Premkumar.ShortenURL.entity.Url;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;


@Component
public interface URLService {


    public CompactURlObject getShortenUrl(CompactURlObject urlStr);
    public String checkshortestURL(String urlStr) throws Exception;
}
