package com.kent.zhifoudaily.entity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StoriesBean {

    public StoriesBean(String headerDate, boolean isHeader) {
        this.headerDate = headerDate;
        this.isHeaderDate = isHeader;
    }

    public StoriesBean(){}

    private String headerDate;
    private boolean isHeaderDate;

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private boolean multipic;

    public static StoriesBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), StoriesBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isHeaderDate() {
        return isHeaderDate;
    }

    public void setHeaderDate(boolean headerDate) {
        isHeaderDate = headerDate;
    }

    public String getHeaderDate() {
        return headerDate;
    }

    public void setHeaderDate(String headerDate) {
        this.headerDate = headerDate;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}