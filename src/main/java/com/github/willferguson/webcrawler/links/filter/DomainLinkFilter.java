package com.github.willferguson.webcrawler.links.filter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Only allows links from specific domains to be followed.
 * Created by will on 15/03/17.
 */
public class DomainLinkFilter implements LinkFilter {

    private List<String> domains;

    public DomainLinkFilter(String... domains) {
       this.domains = Arrays.stream(domains)
               .map(domain -> {
                   try {
                       return new URL(domain).getHost();
                   }
                   catch (IOException e) {
                       //Might as well bail as this would indicate a bad configuration
                       throw new RuntimeException(e);
                   }
               })
               .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * Matches on {@link URL#getHost} so ignores protocol.
     * @param url The url to follow
     * @return
     */
    @Override
    public boolean shouldFollow(URL url) {
        return domains.stream()
                .anyMatch(domain -> url.getHost().equals(domain));
    }
}
