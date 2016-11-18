package com.kent.zhifoudaily.entity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Kent ↗↗↗ on 2016/10/31.
 */

public class NewsLatest {

    /**
     * date : 20161031
     * stories : [{"images":["http://pic1.zhimg.com/b42b4379ea1fc29a1691fc76ef327120.jpg"],"type":0,"id":8936934,"ga_prefix":"103120","title":"强调保护知识产权，确实有可能是「不合理」的"},{"images":["http://pic1.zhimg.com/378ed297ae49db3be63041f3c3310c24.jpg"],"type":0,"id":8936571,"ga_prefix":"103119","title":"「拖延症」跟「懒」是一回事吗？"},{"images":["http://pic2.zhimg.com/e2a40b9590140290b1895523bad5f1c9.jpg"],"type":0,"id":8933966,"ga_prefix":"103118","title":"想成为产品经理，俞军有些好建议"},{"images":["http://pic3.zhimg.com/36253900d0ac65850e5e45b0b412c97a.jpg"],"type":0,"id":8936451,"ga_prefix":"103117","title":"知乎好问题 · 如何低成本化妆成丧尸？"},{"images":["http://pic1.zhimg.com/4d5173ce56066f6cc32ac76e77542ab4.jpg"],"type":0,"id":8936232,"ga_prefix":"103116","title":"天天刷屏的网络红人们都是靠什么赚钱的？"},{"title":"客厅就那么大，教你几招可以看起来更高更宽敞","ga_prefix":"103115","images":["http://pic1.zhimg.com/2d7de68b203110440a645cc8ba02fae8.jpg"],"multipic":true,"type":0,"id":8924641},{"images":["http://pic4.zhimg.com/ec988b38950bebc68ea4a32e0fc7997f.jpg"],"type":0,"id":8929763,"ga_prefix":"103114","title":"回看周杰伦蔡依林那首《布拉格广场》，不愧当时最高水准"},{"images":["http://pic3.zhimg.com/459d87c3a26a95b787bc913c79fde7a6.jpg"],"type":0,"id":8933838,"ga_prefix":"103113","title":"万圣节特辑 · 你真的不迷信吗？"},{"images":["http://pic4.zhimg.com/6d0e3722900363b17a06c0f0fa740067.jpg"],"type":0,"id":8933860,"ga_prefix":"103112","title":"大误 · 给我一双翅膀"},{"images":["http://pic4.zhimg.com/7cb19a84c01cf9e49e255a40dd1ea557.jpg"],"type":0,"id":8934337,"ga_prefix":"103111","title":"突然顿悟，是这些神经活动的功劳"},{"title":"入冬了，来碗酸菜骨头汤补补","ga_prefix":"103110","images":["http://pic4.zhimg.com/d8650b12c2437d3a35273226a52745f3.jpg"],"multipic":true,"type":0,"id":8931934},{"images":["http://pic3.zhimg.com/f10aafe7aff122039019e0a1d979c866.jpg"],"type":0,"id":8934125,"ga_prefix":"103109","title":"1929 年，美国的经济到底发生了什么？"},{"images":["http://pic4.zhimg.com/1b43b077b85b175df405dca5d130126f.jpg"],"type":0,"id":8925022,"ga_prefix":"103108","title":"用一个笑话带你理解看起来很神奇的「无土栽培」"},{"images":["http://pic1.zhimg.com/0c34c59fc2de981da5c2be32928dbebc.jpg"],"type":0,"id":8934305,"ga_prefix":"103107","title":"上班时总是担惊受怕，想着努力避开领导"},{"images":["http://pic3.zhimg.com/ec0804c3ffd7e651cdf690e98bd92bbe.jpg"],"type":0,"id":8933841,"ga_prefix":"103107","title":"头破血流好吓人，怎么医生还跟我说「没事」？"},{"title":"开着飞机上天转了一圈，好像比开车也难不了多少嘛","ga_prefix":"103107","images":["http://pic4.zhimg.com/00dcdb7e8c767236d2b1caacbb052fff.jpg"],"multipic":true,"type":0,"id":8928167},{"images":["http://pic3.zhimg.com/238f1c3236fb288348baff8f81034ab6.jpg"],"type":0,"id":8934326,"ga_prefix":"103107","title":"读读日报 24 小时热门 TOP 5 · 中国的铁路上有美食吗？"},{"images":["http://pic1.zhimg.com/909b5036a19c17de322177755a6fcecc.jpg"],"type":0,"id":8928033,"ga_prefix":"103106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/01ae374fb1cba8e6af5e8e38a80ec5bb.jpg","type":0,"id":8936451,"ga_prefix":"103117","title":"知乎好问题 · 如何低成本化妆成丧尸？"},{"image":"http://pic2.zhimg.com/dcd27f9d721750cdf1013cfd1149ab19.jpg","type":0,"id":8936232,"ga_prefix":"103116","title":"天天刷屏的网络红人们都是靠什么赚钱的？"},{"image":"http://pic4.zhimg.com/7b9289ce5d46c7dbe83c1e768b57c4fb.jpg","type":0,"id":8929763,"ga_prefix":"103114","title":"回看周杰伦蔡依林那首《布拉格广场》，不愧当时最高水准"},{"image":"http://pic2.zhimg.com/8daee8c25e252d588ac7a49123f39179.jpg","type":0,"id":8933838,"ga_prefix":"103113","title":"万圣节特辑 · 你真的不迷信吗？"},{"image":"http://pic4.zhimg.com/6c361d33f766ecf8c2c0d3c671ec0c83.jpg","type":0,"id":8928167,"ga_prefix":"103107","title":"开着飞机上天转了一圈，好像比开车也难不了多少嘛"}]
     */

    private String date;
    /**
     * images : ["http://pic1.zhimg.com/b42b4379ea1fc29a1691fc76ef327120.jpg"]
     * type : 0
     * id : 8936934
     * ga_prefix : 103120
     * title : 强调保护知识产权，确实有可能是「不合理」的
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/01ae374fb1cba8e6af5e8e38a80ec5bb.jpg
     * type : 0
     * id : 8936451
     * ga_prefix : 103117
     * title : 知乎好问题 · 如何低成本化妆成丧尸？
     */

    private List<TopStoriesBean> top_stories;

    public static NewsLatest objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), NewsLatest.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public static TopStoriesBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), TopStoriesBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }
}
