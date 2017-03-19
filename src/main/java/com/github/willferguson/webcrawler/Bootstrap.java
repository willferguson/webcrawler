package com.github.willferguson.webcrawler;

import com.github.willferguson.webcrawler.crawlers.SimpleWebCrawler;
import com.github.willferguson.webcrawler.crawlers.WebCrawler;
import com.github.willferguson.webcrawler.links.LinkManager;
import com.github.willferguson.webcrawler.links.filter.DomainLinkFilter;
import com.github.willferguson.webcrawler.model.WebPage;
import com.github.willferguson.webcrawler.printers.SimpleStdOutPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Entry point to the application
 * Created by will on 17/03/2017.
 */
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws IOException {

        logger.info("Starting WebCrawler with {}", args[0]);
        URL url = new URL(args[0]);

        //Restrict the crawling to the single domain
        DomainLinkFilter domainLinkFilter = new DomainLinkFilter(url.getProtocol() + "://" + url.getHost());
        LinkManager linkManager = new LinkManager(domainLinkFilter);
        WebCrawler webCrawler = new SimpleWebCrawler(linkManager);

        List<WebPage> crawledPages = webCrawler.crawlSite(url);

        logger.info("Crawling complete, formatting and printing...");
        new SimpleStdOutPrinter().print(crawledPages);
    }
}
