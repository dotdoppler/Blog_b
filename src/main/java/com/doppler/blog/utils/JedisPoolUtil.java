package com.doppler.blog.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by doppler on 2016/8/24.
 */
public class JedisPoolUtil {

    private static final String POOL_HOST = "127.0.0.1";
    private static final int HOST_PORT = 6379;
    private volatile static JedisPool jedisPool;
    private JedisPoolUtil(){}

    /**
     * 单例模式, 获取连接池
     * @return
     */
    public static JedisPool getJedisPoolInstance(){
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    //连接池配置
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);    //最大实例连接数
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig,POOL_HOST,HOST_PORT);
                    return jedisPool;
                }
            }
        }

        return jedisPool;
    }

    /**
     * 资源返回连接池
     * @param jedisPool
     * @param jedis
     */
    public static void returnResource(JedisPool jedisPool ,Jedis jedis){
        if (jedis != null) {
            System.out.println("关闭连接池");
            jedisPool.close();
        }
    }
}

