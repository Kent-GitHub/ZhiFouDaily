package com.kent.zhifoudaily.entity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class StoryExtra {

    /**
     * long_comments : 0
     * popularity : 1
     * short_comments : 0
     * comments : 0
     */

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public static StoryExtra objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), StoryExtra.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
