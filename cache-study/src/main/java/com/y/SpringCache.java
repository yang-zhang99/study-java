package com.y;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SpringCache {

    @Cacheable(cacheNames = "UCS_SCENE_SEND_TOTAL_COUNT_CACHE", key = "#p0", sync = true)
    public int cacheSceneCallLimit(String key, int count) {
        return count;
    }

    @CachePut(cacheNames = "UCS_SCENE_SEND_TOTAL_COUNT_CACHE", key = "#p0")
    public int editSceneCallLimit(String key, int callLimit) {
        return callLimit;
    }
}
