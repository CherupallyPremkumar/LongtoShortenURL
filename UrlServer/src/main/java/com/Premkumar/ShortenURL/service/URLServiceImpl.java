package com.Premkumar.ShortenURL.service;

import com.Premkumar.ShortenURL.builder.Converstion;
import com.Premkumar.ShortenURL.dto.CompactURlObject;
import com.Premkumar.ShortenURL.entity.Url;
import com.Premkumar.ShortenURL.repository.UrlRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class URLServiceImpl implements URLService{
    @Autowired
    private UrlRepo urlRepo;

    @Override
    public CompactURlObject getShortenUrl(CompactURlObject urlStr) {
        Random random = new Random();
        Url url = Converstion.dtoTOEntity(urlStr);
        url.setUrlId(random.nextInt(1, 100000));
        url.setLongUrl(urlStr.getLongUrl());

        try {
            Url savedUrl = urlRepo.save(url);
            String str = new LogicImplementation().encode(savedUrl.getUrlId());
            return new CompactURlObject("localhost:8087/" + str);
        } catch (Exception e) {
            // Handle any exception that occurs during save
            e.printStackTrace();
            return null; // or return an error response if needed
        }
    }

    @Override
    public String checkshortestURL(String urlStr) {
        try {
            long s1 = new LogicImplementation().decode(urlStr);
            Url obj = urlRepo.findById((int) s1)
                    .orElseThrow(() -> new EntityNotFoundException("There is no entity with urlId: " + s1));

            if (obj.getCraeated_date() != null && new Date().getTime() - obj.getCraeated_date().getTime() > 15) {
                urlRepo.delete(obj);
                return "Time Expired";
            } else {
                return obj.getLongUrl();
            }
        } catch (EntityNotFoundException ex) {
            return "URL not found";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "An error occurred"; // or return a more specific error message if needed
        }
    }


}
