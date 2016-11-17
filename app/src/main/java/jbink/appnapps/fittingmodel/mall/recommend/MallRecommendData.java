package jbink.appnapps.fittingmodel.mall.recommend;

/**
 * Created by user on 2016-11-14.
 */
public class MallRecommendData {
    private String picture;
    private String shop;
    private String cost;
    private String content;
    private String time;
    private boolean bookmark;

    public MallRecommendData(String picture, String shop, String cost, String content, String time, boolean bookmark) {
        this.picture = picture;
        this.shop = shop;
        this.cost = cost;
        this.content = content;
        this.time = time;
        this.bookmark = bookmark;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
