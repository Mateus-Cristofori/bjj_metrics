package com.bjj_metrics_brasil.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    void save(String key, String value);
    void save(String key, String value, long timeout, TimeUnit unit);
    String get(String key);
    boolean exists(String key);
    void delete(String key);
}
