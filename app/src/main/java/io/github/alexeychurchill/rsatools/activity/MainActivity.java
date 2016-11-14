package io.github.alexeychurchill.rsatools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import io.github.alexeychurchill.rsatools.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = ((TabHost) findViewById(R.id.tabHost));
        if (tabHost != null) {
            tabHost.setup();

            TabHost.TabSpec generatorTabSpec = tabHost.newTabSpec("generatorTabTag");
            generatorTabSpec.setIndicator("Random");
            generatorTabSpec.setContent(R.id.fragmentGenerator);
            tabHost.addTab(generatorTabSpec);

            tabHost.setCurrentTab(0);
        }
    }
}
