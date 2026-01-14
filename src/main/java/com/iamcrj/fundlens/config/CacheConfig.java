package com.iamcrj.fundlens.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("nseIpoCache") {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        new ConcurrentHashMap<>(),
                        false
                );
            }
        };
    }
}
