package com.bircan.korhan.githubbrowser;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ftq194 on 3/13/17.
 */

public class NetworkUtilities {
    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    final static String SORTY_BY = "stars";

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

    /**
     * Method to return HTTP response as a string.
     * @param url The URL to get the HTTP response from.
     * @return The HTTP response as a string.
     * @throws IOException
     */
    public static String stringResponseFrom(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasNext = scanner.hasNext();
            if (hasNext) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
