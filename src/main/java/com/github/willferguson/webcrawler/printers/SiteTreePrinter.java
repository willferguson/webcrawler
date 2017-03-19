package com.github.willferguson.webcrawler.printers;

import com.github.willferguson.webcrawler.model.WebPage;

import java.util.List;

/**
 * Describes the process of printing a list of {@link WebPage}s
 * to an output stream
 * Created by will on 19/03/2017.
 */
public interface SiteTreePrinter {

    void print(List<WebPage> pages);
}
