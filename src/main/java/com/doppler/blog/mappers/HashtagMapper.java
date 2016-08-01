package com.doppler.blog.mappers;

import com.doppler.blog.models.Hashtag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by doppler on 2016/7/4.
 */
@Mapper
public interface HashtagMapper {
    Hashtag findTagByName(@Param("name") String name);
    List<Hashtag> findAllTags();
    void deleteTagById(@Param("id") Long hashtagId);

    void insertHashTag(Hashtag hashtag);

    void savePostAndTags(@Param("tagId") Long hashtagId,
                         @Param("id") Long id);
}
