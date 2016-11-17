package jbink.appnapps.fittingmodel.mall.ranking;

/**
 * Created by user on 2016-11-14.
 */
public class MallRankingData {
    private int rank;
    private String picture;
    private String shop;
    private String content;
    private int match;
    private int review;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public MallRankingData(int rank, String picture, String shop, String content, int match, int review) {
        this.rank = rank;
        this.picture = picture;
        this.shop = shop;
        this.content = content;
        this.match = match;
        this.review = review;
    }
}
