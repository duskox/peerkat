package com.getpeerkat.peerkat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.getpeerkat.peerkat.daggermodules.DataComponent;
import com.getpeerkat.peerkat.data.PeerkatPreferences;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    // Dependencies
    @Inject
    PeerkatPreferences preferences;

    //Views
    @Bind(R.id.mainAndOnlyText)
    TextView onlyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeerkatApp.getDataComponent().inject(this);

        ButterKnife.bind(this);

        onlyText.setText((preferences.isFirstRun()) ? "First run!" : "Not the first run :(");
        preferences.firstRunExecuted();
    }
}
