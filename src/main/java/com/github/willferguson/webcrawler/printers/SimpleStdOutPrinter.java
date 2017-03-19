package com.github.willferguson.webcrawler.printers;

import com.github.willferguson.webcrawler.model.WebPage;

import java.util.List;

/**
 * Prints the tree in a simple list to stdout.
 * Created by will on 19/03/2017.
 */
public class SimpleStdOutPrinter implements SiteTreePrinter {

    @Override
    public void print(List<WebPage> pages) {

        pages.forEach(page -> {
            System.out.println("Page " + page.getUrl());
            System.out.println();

            page.getHyperLinks()
                    .forEach(link -> {
                        System.out.println(String.format("\tHyperLink: %s (%s)", link.getHref(), link.getLinkText()));
                    });
            System.out.println();

            page.getImages()
                    .forEach(image -> {
                        System.out.println(String.format("\tImage: %s (%s)", image.getSrc(), image.getAltText()));
                    });

            System.out.println();

            page.getLinks()
                    .forEach(link -> {
                        System.out.println(String.format("\tLink: %s (%s)", link.getHref(), link.getRel()));
                    });

            System.out.println();

            page.getScripts()
                    .forEach(script -> {
                        System.out.println(String.format("\tScript: %s", script.getSrc()));
                    });

            System.out.println();
        });
    }
}
