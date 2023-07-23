package com.Premkumar.ShortenURL.builder;

import com.Premkumar.ShortenURL.dto.CompactURlObject;
import com.Premkumar.ShortenURL.entity.Url;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Converstion {



    public static Url dtoTOEntity(CompactURlObject compactURlObject)
    {
       return   Url.builder()
               .longUrl(compactURlObject.getLongUrl())
               .craeated_date(new Date())
               .build();
    }

}
