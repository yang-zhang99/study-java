package com.y;

import com.github.benmanes.caffeine.cache.Cache;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class SpringCacheTest {

    @Resource
    public SpringCache springCache;

    @Resource
    public CacheManager cacheManager;

    public void sub(String key) {
        CaffeineCache cache =
                (CaffeineCache) cacheManager.getCache(key);
        if (cache != null) {
            Cache<Object, Object> nativeCache = cache.getNativeCache();
            long size = nativeCache.estimatedSize();
            System.out.println("cache size=" + size);
            nativeCache.asMap().forEach((k, v) -> {
                System.out.println("key=" + k + " value=" + v);
            });
        }
    }

    public String test(String key, Integer cacheLimit) {
        int cache = springCache.cacheSceneCallLimit(key, 0);

//        this.sub("UCS_SCENE_SEND_TOTAL_COUNT_CACHE");

        if (cache >= cacheLimit) {
            return "达到缓存上限";
        }
        springCache.editSceneCallLimit(key, cache + 1);
        return "调用成功" + "cache=" + cache;
    }

    @Test
    public void testSimpleLimit() {
        String key = "scene_A";
        int callLimit = 50;
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "次数" + test(key, callLimit));
            ;
        }
    }


//    @Test
//    void testConcurrentLimit() throws InterruptedException {
//        int threadCount = 100;
//        String key = "scene_A";
//        // 使用 AtomicInteger 模拟远程缓存的原子性变化，便于观察测试结果
//        AtomicInteger mockCacheStore = new AtomicInteger(0);
//
//        // 模拟读取操作
//        lenient().when(springCache.cacheSceneCallLimit(eq(key), anyInt()))
//                .thenAnswer(invocation -> mockCacheStore.get());
//
//        // 模拟写入操作
//        lenient().doAnswer(invocation -> {
//            int newValue = invocation.getArgument(1);
//            mockCacheStore.set(newValue);
//            return null;
//        }).when(springCache).editSceneCallLimit(eq(key), anyInt());
//
//        // 使用 CountDownLatch 确保所有线程同时开始
//        CountDownLatch latch = new CountDownLatch(1);
//        CountDownLatch doneSignal = new CountDownLatch(threadCount);
//        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//
//        for (int i = 0; i < threadCount; i++) {
//            executor.submit(() -> {
//                try {
//                    latch.await(); // 等待发令枪
//                    this.test(key, 50);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                } finally {
//                    doneSignal.countDown();
//                }
//            });
//        }
//
//        latch.countDown(); // 多个线程同时冲锋
//        doneSignal.await(5, TimeUnit.SECONDS);
//
//        System.out.println("最终缓存值: " + mockCacheStore.get());
//        // 理论上如果线程安全，此处应等于 50。
//        // 但在你当前的代码逻辑下，这里大概率会大于 50。
//        assertTrue(mockCacheStore.get() <= 50, "并发下超过了限制阈值！");
//    }

}
