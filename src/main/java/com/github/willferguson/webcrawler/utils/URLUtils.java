package com.github.willferguson.webcrawler.utils;

/**
 * Utility methods on URLs
 * Created by will on 19/03/2017.
 */
public class URLUtils {

    /**
     * Appends a trailing / if not existing.
     * Useful to align urls / paths / hosts to a common format
     * @param str
     * @return
     */
    public static String appendTrailingSlash(String str) {
        if (!str.endsWith("/")) {
            return str + "/";
        }
        else return str;
    }

}
