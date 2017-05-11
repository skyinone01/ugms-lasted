package com.ug369.backend.bean.bean.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2017/5/10.
 */
public class ArticleColumnRequest {

    private long id;
    private String title;
    private String picture;
    private boolean paymode;
    private PayItem payItem1;
    private PayItem payItem2;
    private PayItem payItem3;
    private List<SimpleArticle> articles = new ArrayList<>();

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPaymode() {
        return paymode;
    }

    public void setPaymode(boolean paymode) {
        this.paymode = paymode;
    }

    public PayItem getPayItem1() {
        return payItem1;
    }

    public void setPayItem1(int price, int months) {
        PayItem payItem = new PayItem();
        payItem.setMonth(months);
        payItem.setPrice(price);
        this.payItem1 = payItem;
    }
    public void setPayItem2(int price, int months) {
        PayItem payItem = new PayItem();
        payItem.setMonth(months);
        payItem.setPrice(price);
        this.payItem2 = payItem;
    }
    public void setPayItem3(int price, int months) {
        PayItem payItem = new PayItem();
        payItem.setMonth(months);
        payItem.setPrice(price);
        this.payItem3 = payItem;
    }

    public void setPayItem1(PayItem payItem1) {
        this.payItem1 = payItem1;
    }

    public PayItem getPayItem2() {
        return payItem2;
    }

    public void setPayItem2(PayItem payItem2) {
        this.payItem2 = payItem2;
    }

    public PayItem getPayItem3() {
        return payItem3;
    }

    public void setPayItem3(PayItem payItem3) {
        this.payItem3 = payItem3;
    }

    public List<SimpleArticle> getArticles() {
        return articles;
    }

    public void getArticles(List<SimpleArticle> articles) {
        this.articles = articles;
    }

    public void setArticles(String title,long id) {
        SimpleArticle article = new SimpleArticle();
        article.setId(id);
        article.setTitle(title);
        articles.add(article);
    }

    public static class PayItem {
        private int price;
        private int month;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    }

    public static class SimpleArticle {
        private long id;
        private String title;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
