package com.github.willferguson.webcrawler.crawlers;

import com.github.willferguson.webcrawler.model.WebPage;

import java.io.IOException;
import java.net.URL;

/**
 * Describes the contract of crawling a single page for content
 * Created by will on 15/03/17.
 */
public interface PageCrawler {

    /**
     * Crawls a single url, returning a {@link WebPage} describing the contents of the page
     * @param url the fully qualified url to crawl
     * @return A {@link WebPage} entity modelling the page crawled.
     * @throws IOException if the url is invalid, or the resource at the url could not be parsed
     */
    WebPage crawl(URL url) throws IOException;
}
