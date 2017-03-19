package com.github.willferguson.webcrawler.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a web page which is the result of a crawl of a single url.
 *
 * Created by will on 15/03/17.
 */
public class WebPage {

    private URL url;
    private List<HyperLink> hyperLinks = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private List<Script> scripts = new ArrayList<>();
    private List<Image> images = new ArrayList<>();


    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public List<HyperLink> getHyperLinks() {
        return hyperLinks;
    }

    public void setHyperLinks(List<HyperLink> hyperLinks) {
        this.hyperLinks = hyperLinks;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
