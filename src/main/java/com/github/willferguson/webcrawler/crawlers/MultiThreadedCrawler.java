package com.github.willferguson.webcrawler.crawlers;

import com.github.willferguson.webcrawler.links.LinkManager;
import com.github.willferguson.webcrawler.model.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Multithreaded web crawler which uses a {@link Phaser}
 * to coordinate completion of all tasks.
 * Created by will on 20/03/17.
 *
 * TODO Fix the encapsulation issues.
 * TODO Pass number of threads a config
 * TODO Shutdown the executor!
 */
public class MultiThreadedCrawler implements WebCrawler {

    private Phaser phaser = new Phaser();
    private List<WebPage> pages = new ArrayList<>();
    private LinkManager linkManager;
    private ExecutorService executorService;

    public MultiThreadedCrawler(LinkManager linkManager, ExecutorService executorService) {
        this.linkManager = linkManager;
        this.executorService = executorService;
    }

    @Override
    public List<WebPage> crawlSite(URL rootUrl) {

        //Register self
        phaser.register();

        //Submit root task which will spawn new tasks until complete
        executorService.submit(new CrawlTask(rootUrl));

        //Wait until all the tasks have completed
        phaser.arriveAndAwaitAdvance();

        executorService.shutdown();
        return pages;
    }
    /*
     * Prevents a race condition when multiple threads want to check if they should follow the same link
     */
    private synchronized boolean checkAndSubmit(URL url) {
        if (linkManager.shouldFollow(url)) {
            linkManager.submit(url);
            return true;
        }
        else return false;
    }

    /**
     * Crawls a single URL, and for every interested link, it creates a new instance and submits.
     */
    private class CrawlTask implements Runnable {

        private Logger logger = LoggerFactory.getLogger(CrawlTask.class);

        private URL url;

        CrawlTask(URL url) {
            phaser.register();
            this.url = url;
        }

        @Override
        public void run() {
            try {
                WebPage page = new PageCrawlerImpl().crawl(url);
                pages.add(page);
                page.getHyperLinks()
                        .forEach(
                                link -> {
                                    try {
                                        URL url = new URL(link.getHref());
                                        if (checkAndSubmit(url)) {
                                            executorService.submit(new CrawlTask(url));
                                        }
                                    } catch (MalformedURLException e) {
                                        logger.info("Error converting URL href [{}] with link text [{}]", link.getHref(), link.getLinkText());
                                    }
                                }
                        );
                phaser.arrive();
            } catch (IOException e) {
                phaser.arrive();
                throw new RuntimeException(e);
            }


        }
    }



}
