package com.getpeerkat.peerkat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Move this to where you establish a user session
        logUser();

        setContentView(R.layout.activity_main);
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods

    }


}
