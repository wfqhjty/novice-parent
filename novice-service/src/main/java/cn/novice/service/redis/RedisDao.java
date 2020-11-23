package cn.novice.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository("redisDao")
public class RedisDao {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setExpire(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 2, TimeUnit.HOURS);
    }

    public Map<Object, Object> hget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void hset(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
}
