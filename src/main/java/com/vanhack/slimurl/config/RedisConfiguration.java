package com.vanhack.slimurl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    private static final String REDIS_URL = "REDIS_URL";

    @Bean
    JedisConnectionFactory jedisConnectionFactory()  {
        final String redisURL = System.getenv(REDIS_URL);
        final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisURL.split("@")[1].replaceFirst(":[0-9]{5}",""));
        jedisConnectionFactory.setPort(Integer.valueOf(redisURL.split(":")[3]));
        jedisConnectionFactory.setClientName(redisURL.split("//")[1].split(":")[0]);
        jedisConnectionFactory.setPassword(redisURL.split("//")[1].split(":")[1].split("@")[0]);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
