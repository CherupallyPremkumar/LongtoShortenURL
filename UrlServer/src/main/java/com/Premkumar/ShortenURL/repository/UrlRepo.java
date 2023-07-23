package com.Premkumar.ShortenURL.repository;

import com.Premkumar.ShortenURL.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<Url, Integer> {




}
