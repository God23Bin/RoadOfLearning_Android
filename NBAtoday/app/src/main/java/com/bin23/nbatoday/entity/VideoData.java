package com.bin23.nbatoday.entity;

import java.util.List;

public class VideoData {
    /**
     * type : video
     * id : 70602017
     * author : 今天来点土豆吧
     * mid : 43304793
     * typeid : 163
     * typename : 运动
     * arcurl : http://www.bilibili.com/video/av70602017
     * aid : 70602017
     * bvid : BV1FE411f7SE
     * title : 央视主播说联播再批<em class="keyword">NBA</em> ，肖华就是笑话！<em class="keyword">NBA</em>不过就是个球而已
     * description : https://m.weibo.cn/2656274875/4425248377738174
     * arcrank : 0
     * pic : //i0.hdslb.com/bfs/archive/8091e11c1cd256161f11939ac11a952111b9c9c1.jpg
     * play : 530563
     * video_review : 903
     * favorites : 1325
     * tag : 央视,新闻联播,NBA,莫雷,亚当肖华,火箭队
     * review : 1103
     * pubdate : 1570547616
     * senddate : 1570547616
     * duration : 1:24
     * badgepay : false
     * hit_columns : ["title","tag"]
     * view_type :
     * is_pay : 0
     * is_union_video : 0
     * rec_tags : null
     * new_rec_tags : []
     * rank_score : 104595878
     */

    private String type;
    private int id;
    private String author;
    private int mid;
    private String typeid;
    private String typename;
    private String arcurl;
    private int aid;
    private String bvid;
    private String title;
    private String description;
    private String arcrank;
    private String pic;
    private int play;
    private int video_review;
    private int favorites;
    private String tag;
    private int review;
    private int pubdate;
    private int senddate;
    private String duration;
    private boolean badgepay;
    private String view_type;
    private int is_pay;
    private int is_union_video;
    private Object rec_tags;
    private int rank_score;
    private List<String> hit_columns;
    private List<?> new_rec_tags;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getBvid() {
        return bvid;
    }

    public void setBvid(String bvid) {
        this.bvid = bvid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArcrank() {
        return arcrank;
    }

    public void setArcrank(String arcrank) {
        this.arcrank = arcrank;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getVideo_review() {
        return video_review;
    }

    public void setVideo_review(int video_review) {
        this.video_review = video_review;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
        this.pubdate = pubdate;
    }

    public int getSenddate() {
        return senddate;
    }

    public void setSenddate(int senddate) {
        this.senddate = senddate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isBadgepay() {
        return badgepay;
    }

    public void setBadgepay(boolean badgepay) {
        this.badgepay = badgepay;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getIs_union_video() {
        return is_union_video;
    }

    public void setIs_union_video(int is_union_video) {
        this.is_union_video = is_union_video;
    }

    public Object getRec_tags() {
        return rec_tags;
    }

    public void setRec_tags(Object rec_tags) {
        this.rec_tags = rec_tags;
    }

    public int getRank_score() {
        return rank_score;
    }

    public void setRank_score(int rank_score) {
        this.rank_score = rank_score;
    }

    public List<String> getHit_columns() {
        return hit_columns;
    }

    public void setHit_columns(List<String> hit_columns) {
        this.hit_columns = hit_columns;
    }

    public List<?> getNew_rec_tags() {
        return new_rec_tags;
    }

    public void setNew_rec_tags(List<?> new_rec_tags) {
        this.new_rec_tags = new_rec_tags;
    }
}
