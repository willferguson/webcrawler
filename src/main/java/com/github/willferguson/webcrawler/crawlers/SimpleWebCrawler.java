package com.github.willferguson.webcrawler.crawlers;

import com.github.willferguson.webcrawler.links.LinkManager;
import com.github.willferguson.webcrawler.model.HyperLink;
import com.github.willferguson.webcrawler.model.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Single threaded web crawler using a {@link LinkManager}
 * to decide whether we would want to crawl a URL.
 *
 * Created by will on 15/03/17.
 */
public class SimpleWebCrawler implements WebCrawler {

    private Queue<URL> urlQueue = new LinkedList<>();
    private Logger logger = LoggerFactory.getLogger(SimpleWebCrawler.class);

    private LinkManager linkManager;


    public SimpleWebCrawler(LinkManager linkManager) {
        this.linkManager = linkManager;
    }

    @Override
    public List<WebPage> crawlSite(URL rootUrl) {

        List<WebPage> pages = new ArrayList<>();

        submitAndQueue(rootUrl);

        for (URL url; (url = urlQueue.poll()) != null;) {
            try {
                //Crawl the url
                WebPage page = new PageCrawlerImpl()
                        .crawl(url);

                pages.add(page);
                //If we have any links which we're interested in, then add to the queue
                page.getHyperLinks()
                        .stream()
                        .map(HyperLink::getHref)
                        .forEach(link -> {
                            try {
                                URL urlLink = new URL(link);
                                if (linkManager.shouldFollow(urlLink)) {
                                    logger.debug("Found suitable URL {} for crawling", urlLink);
                                    submitAndQueue(urlLink);
                                }

                            } catch (MalformedURLException e) {
                                logger.info("Invalid URL [{}] - ignoring", link);
                            }
                        });
            } catch (IOException e) {
                logger.warn("Error processing {}", url, e);
            }

        }
        return pages;

    }


    private void submitAndQueue(URL url) {
        linkManager.submit(url);
        urlQueue.add(url);
    }








}
