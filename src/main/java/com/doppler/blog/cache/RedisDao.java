package com.doppler.blog.cache;

import com.doppler.blog.models.Post;
import com.doppler.blog.utils.JedisPoolUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

import static com.doppler.blog.GlobalConstants.CACHE_KEY_POST_ARCHIVE;
import static com.doppler.blog.GlobalConstants.CACHE_MSG_FROM_REDIS;
import static com.dyuproject.protostuff.LinkedBuffer.DEFAULT_BUFFER_SIZE;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by doppler on 2016/8/24.
 */

@Component
public class RedisDao {
     private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
    private JedisPool jedisPool;
    private Jedis jedis;
    private final RuntimeSchema<PostList> postListSchema = RuntimeSchema.createFrom(PostList.class);
    public List<Post> getPostArchives() {
        PostList postList = null;
        try {
            jedisPool = JedisPoolUtil.getJedisPoolInstance();
            jedis = jedisPool.getResource();
            byte[] data = jedis.get(CACHE_KEY_POST_ARCHIVE.val().getBytes());
            postList = postListSchema.newMessage();
            ProtostuffIOUtil.mergeFrom(data,postList,postListSchema);
        }catch (NullPointerException ex){}
        finally {
            JedisPoolUtil.returnResource(jedis);
        }
        logger.info(CACHE_MSG_FROM_REDIS.val());
        return postList.getPostList();
    }

    public void putPostArchives(List posts) {
        try {
            jedisPool = JedisPoolUtil.getJedisPoolInstance();
            jedis = jedisPool.getResource();
            PostList postList = new PostList();
            postList.setPostList(checkNotNull(posts));
            final byte[] data = ProtostuffIOUtil.toByteArray(postList,postListSchema, LinkedBuffer.allocate(DEFAULT_BUFFER_SIZE));
            String result = jedis.set(CACHE_KEY_POST_ARCHIVE.val().getBytes(),data);
            logger.info("Put data into Redis :" + result);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }finally {
            JedisPoolUtil.returnResource(jedis);
        }
    }
}
