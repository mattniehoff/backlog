package com.mattniehoff.backlog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_search:
                mTextMessage.setText(R.string.title_nav_search);
                return true;
            case R.id.navigation_library:
                mTextMessage.setText(R.string.title_nav_library);
                return true;
            case R.id.navigation_backlog:
                mTextMessage.setText(R.string.title_nav_backlog);
                return true;
            case R.id.navigation_statistics:
                mTextMessage.setText(R.string.title_nav_statistics);
                return true;
        }

        return false;
    }
}
