package com.github.willferguson.webcrawler.model;


/**
 * Models an image tag
 * Created by will on 19/03/2017.
 */
public class Image {

    private String src;
    private String altText;

    public Image(String src, String altText) {
        this.src = src;
        this.altText = altText;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
}
