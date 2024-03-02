package com.sellist.flashcards.service.cache;

import com.sellist.flashcards.data.yaml.YamlCacheInitializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {
    //    schedule cache updates and on startup
    @Autowired
    YamlCacheInitializer yamlCacheInitializer;

    @PostConstruct
    public void init() {
        yamlCacheInitializer.loadCaches();
    }

}
