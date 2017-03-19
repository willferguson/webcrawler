package com.github.willferguson.webcrawler.links.filter;

import java.io.IOException;
import java.net.URL;

/**
 * Analyses a link url and decides whether we want to follow it.
 *
 * Created by will on 15/03/17.
 */
public interface LinkFilter {

    /**
     * Determines if a url should be followed (crawled)
     *
     * @param url The url to follow
     * @return whether we want to crawl this link
     */
    boolean shouldFollow(URL url);

}
