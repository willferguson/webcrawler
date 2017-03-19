package com.github.willferguson.webcrawler;

import com.github.willferguson.webcrawler.crawlers.PageCrawler;
import com.github.willferguson.webcrawler.crawlers.PageCrawlerImpl;
import com.github.willferguson.webcrawler.model.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import static org.mockito.Matchers.any;

/**
 * Mocks using PowerMock so we can mock the Jsoup static classes.
 * Created by will on 15/03/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class PageCrawlerImplTest {

    @Test
    public void testCrawl() throws IOException {

        //Mocks the parse method to return a Document representation of the test.html page.
        //This is a lot less work (and easier to read) than having to mock all the different interactions with the JSoup classes.

        InputStream testHtmlInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.html");

        Document doc = Jsoup.parse(testHtmlInputStream, Charset.defaultCharset().name(), "http://www.example.com/");
        testHtmlInputStream.close();

        //Important to keep the static mocking below the above Jsoup.parse(), otherwise that will be a mock.
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class)))
                .thenReturn(doc);

        PageCrawler pageCrawler = new PageCrawlerImpl();
        WebPage webPage = pageCrawler.crawl(new URL("http://www.example.com/"));

        Assert.assertEquals("Incorrect path", "http://www.example.com/", webPage.getUrl().toString());

        Assert.assertEquals("Should be 1 hyperlink", 1, webPage.getHyperLinks().size());
        Assert.assertEquals("Incorrect hyperlink href", "http://www.example.com/test.html", webPage.getHyperLinks().get(0).getHref());

        Assert.assertEquals("Should be 1 image", 1, webPage.getImages().size());
        Assert.assertEquals("Incorrect image src", "http://www.example.com/test.jpeg", webPage.getImages().get(0).getSrc());

        Assert.assertEquals("Should be 1 script tag", 1, webPage.getScripts().size());
        Assert.assertEquals("Incorrect script src", "http://www.example.com/test.js", webPage.getScripts().get(0).getSrc());

        Assert.assertEquals("Should be 1 link", 1, webPage.getLinks().size());
        Assert.assertEquals("Incorrect href", "http://www.example.com/test.css", webPage.getLinks().get(0).getHref());









    }


}