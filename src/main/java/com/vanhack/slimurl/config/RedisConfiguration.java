package com.vanhack.slimurl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.URL;

@Configuration
public class RedisConfiguration {

    private static final String REDIS_URL = "REDIS_URL";

    @Bean
    JedisConnectionFactory jedisConnectionFactory()  {
        final String redisURL = System.getenv(REDIS_URL);
        System.out.println(redisURL);
        final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        URL url = null;
        try {
            url = new URL(redisURL);
            jedisConnectionFactory.setHostName(url.getHost());
            jedisConnectionFactory.setPort(url.getPort());
            jedisConnectionFactory.setClientName("h");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
