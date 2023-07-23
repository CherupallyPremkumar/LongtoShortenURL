package com.Premkumar.ShortenURL.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

@Entity
@Builder
@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
@Table(name = "url")
public class Url {
    @Id
    @Column(name = "urlId")
    int urlId;
    @Column(name = "longurl")
    String longUrl;
    @Column(name = "created_at")
    Date craeated_date;


}
