package com.kent.zhifoudaily.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kent ↗↗↗ on 2016/10/31.
 */

public class StartImage {

    /**
     * text : Alex Blăjan
     * img : https://pic3.zhimg.com/v2-5ac59d94382fcb23eae38ef269119926.jpg
     */

    private String text;
    private String img;

    public static StartImage objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new com.google.gson.Gson().fromJson(jsonObject.getString(str), StartImage.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
