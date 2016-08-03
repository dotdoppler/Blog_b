package com.doppler.blog;

/**
 * Created by doppler on 2016/5/30.
 */
public enum GlobalConstants {
    INSERT_POST("Insert a post : "),
    DELETE_POST("Delete a post : "),
    UPDATE_POST("Update a post : "),
    UPDATE_PWD("User updated password"),
    UPDATE_SETTINGS("App info updated"),
    INSERT_HASHTAG("Insert a hashtag : "),
    INSERT_POST_FAIL("Insert post failed"),
    UPDATE_USER_FAIL("update user failed"),
    UPDATE_SETTINGS_FAIL("Update settings failed"),
    DELETE_TAG_FAIL("Delete tag failed"),
    DELETE_POST_FAIL("Delete post failed"),
    UPDATE_POST_FAIL("Update post failed");

    private final String value;
    private GlobalConstants(String value){
        this.value = value;
    }
    public String value(){
        return this.value;
    };
}
