package com.sellist.flashcards.service.cache.src;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {
    //    schedule cache updates and on startup
    @Autowired
    MemoryCacheInitializer memoryCacheInitializer;

    @PostConstruct
    public void init() {
        memoryCacheInitializer.loadCaches();
    }

}
