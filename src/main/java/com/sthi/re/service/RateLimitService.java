package com.sthi.re.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    // Key → List of request timestamps
    private final Map<String, List<Long>> requestCache = new ConcurrentHashMap<>();

    @Value("${app.rate-limit.max-requests:3}")
    private int maxRequests;

    @Value("${app.rate-limit.window-seconds:60}")
    private int windowSeconds;

    public void checkRateLimit(String ip, String phone) {

        String key = ip + ":" + phone;
        long now = Instant.now().getEpochSecond();
        long windowStart = now - windowSeconds;

        requestCache.putIfAbsent(key, new CopyOnWriteArrayList<>());
        List<Long> timestamps = requestCache.get(key);

        // Remove timestamps outside window
        timestamps.removeIf(timestamp -> timestamp < windowStart);

        if (timestamps.size() >= maxRequests) {
            throw new IllegalStateException("Rate limit exceeded");
        }

        timestamps.add(now);
    }
}