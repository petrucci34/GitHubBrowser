package com.bircan.korhan.githubbrowser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL gitHubSearchURL = NetworkUtilities.searchURL("swift");
        NetworkUtilities networkUtilities = new NetworkUtilities();
        networkUtilities.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, gitHubSearchURL);
    }
}
