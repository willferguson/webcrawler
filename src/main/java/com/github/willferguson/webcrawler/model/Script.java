package com.github.willferguson.webcrawler.model;

/**
 * Models a <script> tag
 * Created by will on 19/03/2017.
 */
public class Script {

    private String src;

    public Script(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
