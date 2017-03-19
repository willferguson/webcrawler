package com.github.willferguson.webcrawler.crawlers;

import com.github.willferguson.webcrawler.model.WebPage;

import java.net.URL;
import java.util.List;

/**
 * WebCrawler for crawling all pages from a single starting url
 * Created by will on 15/03/2017.
 */
public interface WebCrawler {

    /**
     * Crawl a url and all relevant sub pages
     * @param rootUrl The url from which to start crawling
     * @return The crawled pages.
     */
    List<WebPage> crawlSite(URL rootUrl);
}
