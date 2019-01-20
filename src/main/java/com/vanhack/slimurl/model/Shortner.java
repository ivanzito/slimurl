package com.vanhack.slimurl.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
@Builder
@Data
@RedisHash("shortner")
public class Shortner {

    @Id
    String encoded;

    LocalDateTime localDateTime;
    String url;

}
