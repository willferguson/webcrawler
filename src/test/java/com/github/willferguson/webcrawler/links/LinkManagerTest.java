package com.github.willferguson.webcrawler.links;


import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by will on 16/03/2017.
 */
public class LinkManagerTest {

    LinkManager linkManager;

    @Test
    public void testLinkManager() throws MalformedURLException {

        // We'll just test with a filter which only allows ssl urls
        linkManager = new LinkManager(url -> url.getProtocol().equals("https"));

        Assert.assertFalse("Should not have followed non-https url", linkManager.shouldFollow(new URL("http://www.example.com")));

        URL sslLink = new URL("https://www.example.com");

        Assert.assertTrue("Should be able to follow ssl url", linkManager.shouldFollow(sslLink));

        linkManager.submit(sslLink);

        Assert.assertFalse("Should not be able to follow url we have already submitted", linkManager.shouldFollow(sslLink));




    }

}