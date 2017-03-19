package com.github.willferguson.webcrawler.crawlers;

import com.github.willferguson.webcrawler.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Simple page crawler using the JSoup HTML parsing library
 *
 * Created by will on 15/03/17.
 */
public class PageCrawlerImpl implements PageCrawler {

    private static final int DOCUMENT_PARSE_TIMEOUT_MS = 5000;
    private Logger logger = LoggerFactory.getLogger(PageCrawlerImpl.class);

    @Override
    public WebPage crawl(URL url) throws IOException {

        logger.debug("Crawling {}", url);

        WebPage webPage = new WebPage();
        webPage.setUrl(url);

        Document document = Jsoup.parse(url, DOCUMENT_PARSE_TIMEOUT_MS);

        Elements hyperLinks = document.select("a[href]");
        Elements scripts = document.select("script[src]");
        Elements images = document.select("img[src]");
        Elements links = document.select("link[href]");

        webPage.setHyperLinks(
                hyperLinks.stream()
                    .map(elem -> new HyperLink(elem.attr("abs:href"), elem.text()))
                    .collect(Collectors.toList()));

        webPage.setImages(
                images.stream()
                .map(elem -> new Image(elem.attr("abs:src"), elem.attr("alt")))
                .collect(Collectors.toList()));

        webPage.setLinks(
                links.stream()
                .map(elem -> new Link(elem.attr("abs:href"), elem.attr("rel")))
                .collect(Collectors.toList()));


        webPage.setScripts(
                scripts.stream()
                .map(elem -> new Script(elem.attr("abs:src")))
                .collect(Collectors.toList()));

        return webPage;

    }
}
