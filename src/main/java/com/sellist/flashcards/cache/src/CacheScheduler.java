package com.sellist.flashcards.cache.src;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {
    private final CacheInitializer cacheInitializer;

    @Autowired
    public CacheScheduler(CacheInitializer cacheInitializer) {
        this.cacheInitializer = cacheInitializer;
    }

    @PostConstruct
    public void init() {
        cacheInitializer.loadCaches();
    }

}
