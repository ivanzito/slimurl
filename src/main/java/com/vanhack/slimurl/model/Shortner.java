package com.vanhack.slimurl.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Builder
@Data
@RedisHash("shortner")
public class Shortner {


    @Id
    String encoded;

    LocalDateTime localDateTime;
    String url;

    @TimeToLive(unit = TimeUnit.DAYS)
    long expiration;

}
