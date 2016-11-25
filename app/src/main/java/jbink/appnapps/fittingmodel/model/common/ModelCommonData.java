package jbink.appnapps.fittingmodel.model.common;

/**
 * Created by user on 2016-11-14.
 */
public class ModelCommonData {
    private String picture;
    private String name;
    private String content;
    private String age;
    private String category1;
    private String category2;
    private String time;
    private String address;
    private String bookmark;
    private String recommend;

    /**
     * @param picture
     * @param name
     * @param content
     * @param age
     * @param category1
     * @param category2
     * @param time
     * @param address
     * @param bookmark
     * @param recommend
     */
    public ModelCommonData(String picture, String name, String content, String age, String category1, String category2, String time, String address, String bookmark, String recommend) {
        this.picture = picture;
        this.name = name;
        this.content = content;
        this.age = age;
        this.category1 = category1;
        this.category2 = category2;
        this.time = time;
        this.address = address;
        this.bookmark = bookmark;
        this.recommend = recommend;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
