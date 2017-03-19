package com.github.willferguson.webcrawler.links.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;


/**
 * Created by will on 15/03/17.
 */
public class DomainLinkFilterTest {

    private DomainLinkFilter domainLinkFilter;

    @Test
    public void testSingle() throws IOException {
        domainLinkFilter = new DomainLinkFilter("http://www.example.com");

        Assert.assertTrue("Should have followed", domainLinkFilter.shouldFollow(new URL("http://www.example.com/test.html")));
        Assert.assertFalse("Should not have followed", domainLinkFilter.shouldFollow(new URL("http://www.test.com/test.html")));
    }

    @Test
    public void testMultiple() throws IOException {
        domainLinkFilter = new DomainLinkFilter("http://www.example.com", "http://www.test.com");

        Assert.assertTrue("Should have followed link", domainLinkFilter.shouldFollow(new URL("http://www.example.com/test.html")));
        Assert.assertTrue("Should have followed link ", domainLinkFilter.shouldFollow(new URL("http://www.test.com/test.html")));

        Assert.assertFalse("Should not have followed", domainLinkFilter.shouldFollow(new URL("http://www.abc.com/test.html")));
    }

    @Test
    public void testDifferentProtocol() throws IOException {
        domainLinkFilter = new DomainLinkFilter("http://www.example.com");

        Assert.assertTrue("Should have followed link with https URL", domainLinkFilter.shouldFollow(new URL("https://www.example.com/test.html")));

    }




}