package com.github.willferguson.webcrawler.links;

import com.github.willferguson.webcrawler.links.filter.LinkFilter;
import com.github.willferguson.webcrawler.utils.URLUtils;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Entry point to link tracking and filtering.
 * Enables registration of {@link LinkFilter}s and passes each submitted url
 * to each LinkFilter to determine whether we should traverse this link.
 *
 * Tracks visited links to ensure we don't get stuck in a loop
 * Created by will on 15/03/17.
 */
public class LinkManager {

    private List<LinkFilter> linkFilters;
    private Set<String> visited = new HashSet<>();

    public LinkManager(LinkFilter... linkFilters) {
        this.linkFilters = Arrays.asList(linkFilters);
    }

    /**
     * Tests whether the given url should be followed.
     *
     * If any of the configured filters return false or we've already visited this url then return false
     * @param url The url we want to follow
     * @return Whether this link should be followed.
     */
    public boolean shouldFollow(URL url) {
        return !visited.contains(URLUtils.appendTrailingSlash(url.toString())) && !linkFilters.stream()
                .anyMatch(filter -> !filter.shouldFollow(url));


    }

    /**
     * Submits the url for tracking.
     * It is assumed that all urls submitted are going to be traversed.
     * @param url The URL to be tracked.
     */
    public void submit(URL url) {
        visited.add(URLUtils.appendTrailingSlash(url.toString()));
    }
}
