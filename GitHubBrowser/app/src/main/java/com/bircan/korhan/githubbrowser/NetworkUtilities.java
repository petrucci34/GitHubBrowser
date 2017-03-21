package com.bircan.korhan.githubbrowser;

import android.net.Uri;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ftq194 on 3/13/17.
 */

public class NetworkUtilities {
    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    final static String SORTY_BY = "stars";
    static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return GITHUB_BASE_URL + relativeUrl;
    }

    public static void getRepositories(String keyword) throws JSONException {
        String parameterQuery = "?" + PARAM_QUERY + "=" + keyword + "&sort=stars";
        client.get(parameterQuery, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
//                System.out.println(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
//                try {
//                   JSONObject firstEvent = (JSONObject) timeline.get(0);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                // Do something with the response
                System.out.println(timeline);
            }
        });
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
