package jbink.appnapps.fittingmodel.mall.searchshop;

/**
 * Created by user on 2016-11-15.
 */
public class MallSearchShopData {
    private String picture;
    private String name;
    private String content;

    public MallSearchShopData(String picture, String name, String content) {
        this.picture = picture;
        this.name = name;
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
