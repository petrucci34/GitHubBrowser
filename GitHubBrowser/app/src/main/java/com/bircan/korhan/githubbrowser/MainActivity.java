package com.bircan.korhan.githubbrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL gitHubSearchURL = NetworkUtilities.searchURL("swift");
        try {
            NetworkUtilities.getRepositories("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
