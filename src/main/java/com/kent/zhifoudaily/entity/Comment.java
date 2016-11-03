package com.kent.zhifoudaily.entity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Kent ↗↗↗ on 2016/11/3.
 */

public class Comment {
    /**
     * author : 圆圆的脑袋
     * content : 放西红柿很好吃的，建议你试试
     * avatar : http://pic4.zhimg.com/4544e1884a6a13dd77c1a6d03d60cf77_im.jpg
     * time : 1478169978
     * reply_to : {"content":"复杂了，家常不需要排骨，不明白为什么要西红柿，味道不对啊。","status":0,"id":27097862,"author":"maomao"}
     * id : 27098997
     * likes : 0
     */

    private List<CommentsBean> comments;

    public static Comment objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Comment.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String author;
        private String content;
        private String avatar;
        private int time;
        /**
         * content : 复杂了，家常不需要排骨，不明白为什么要西红柿，味道不对啊。
         * status : 0
         * id : 27097862
         * author : maomao
         */

        private ReplyToBean reply_to;
        private int id;
        private int likes;

        public static CommentsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), CommentsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public static class ReplyToBean {
            private String content;
            private int status;
            private int id;
            private String author;
            private String error_msg;

            public static ReplyToBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ReplyToBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public String getError_msg() {
                return error_msg;
            }

            public void setError_msg(String error_msg) {
                this.error_msg = error_msg;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
