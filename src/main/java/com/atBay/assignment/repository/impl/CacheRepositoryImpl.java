package com.atBay.assignment.repository.impl;


import com.atBay.assignment.config.redis.RedisDetailsConfig;
import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

@Repository
public class CacheRepositoryImpl implements CacheRepository {
    @Autowired
    JedisPooled jedisPool;
    @Autowired
    private RedisDetailsConfig redisDetailsConfig;
    @Override
    public void setKeyAndExpireTime(String key, ScanStatus value) {
        jedisPool.setex(key, redisDetailsConfig.getTtl(), value.name());
    }

    @Override
    public ScanStatus get(String key) {
        if (isKeyExist(key)) {
            return ScanStatus.valueOf(jedisPool.get(key));
        }
        return null;
    }

    @Override
    public void updateStatus(String key, ScanStatus value) {
        if(isKeyExist(key)) {
            jedisPool.setex(key, redisDetailsConfig.getTtl(), value.name());
        }
    }

    @Override
    public Boolean isKeyExist(String key) {
        return jedisPool.exists(key);
    }
}
