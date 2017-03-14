package com.bircan.korhan.githubbrowser;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ftq194 on 3/13/17.
 */

public class NetworkUtilities {
    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    final static String SORTY_BY = "stars";

    public static URL searchURL(String keyword) {
        Uri searchURI = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, keyword)
                .appendQueryParameter(PARAM_SORT, SORTY_BY)
                .build();

        URL url = null;
        try {
            url = new URL(searchURI.toString());
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }

        return url;
    }
}
