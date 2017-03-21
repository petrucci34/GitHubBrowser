package com.bircan.korhan.githubbrowser;

import android.net.Uri;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return GITHUB_BASE_URL + relativeUrl;
    }

    /**
     * Method that returns a search URL with the given keyword.
     * @param keyword Search keyword.
     * @return GitHub search URL with the given keyword.
     */
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
