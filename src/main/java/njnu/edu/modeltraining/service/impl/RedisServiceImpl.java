package njnu.edu.modeltraining.service.impl;

import njnu.edu.modeltraining.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/18/10:35
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void set(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    @Override
    public void set(String key, Object obj, Long time) {
        redisTemplate.opsForValue().set(key, obj, time, TimeUnit.MINUTES);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expire(String key, Long time) {
        redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }
}
