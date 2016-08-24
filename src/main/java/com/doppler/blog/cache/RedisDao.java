package com.doppler.blog.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by doppler on 2016/8/24.
 */

@Component
public class RedisDao {
    private JedisPool jedisPool;
    private Jedis jedis;
}
