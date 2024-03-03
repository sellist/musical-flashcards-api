package com.sellist.flashcards.service.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {
    //    schedule cache updates and on startup
    @Autowired
    CacheInitializer cacheInitializer;

    @PostConstruct
    public void init() {
        cacheInitializer.loadCaches();
    }

}
