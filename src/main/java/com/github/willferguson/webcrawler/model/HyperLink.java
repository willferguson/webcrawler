package com.github.willferguson.webcrawler.model;

/**
 * Models a hyper link - IE <a> tag
 * Created by will on 19/03/2017.
 */
public class HyperLink {

    private String href;
    private String linkText;

    public HyperLink(String href, String linkText) {
        this.href = href;
        this.linkText = linkText;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }
}
