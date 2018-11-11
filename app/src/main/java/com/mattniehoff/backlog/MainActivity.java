package com.mattniehoff.backlog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mattniehoff.backlog.fragments.BacklogFragment;
import com.mattniehoff.backlog.fragments.LibraryFragment;
import com.mattniehoff.backlog.fragments.SearchFragment;
import com.mattniehoff.backlog.fragments.StatisticsFragment;

// Used https://www.youtube.com/watch?v=jpaHMcQDaDg for help setting up bottom navigation
public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        navigateToFirstFragment(savedInstanceState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;
            case R.id.navigation_library:
                fragment = new LibraryFragment();
                break;
            case R.id.navigation_backlog:
                fragment = new BacklogFragment();
                break;
            case R.id.navigation_statistics:
                fragment = new StatisticsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    private void navigateToFirstFragment(Bundle savedInstanceState) {
        // TODO: We could/should set first fragment from savedInstanceState if there's something we save off
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_library);
        } else {
            //TODO: Here is where we do this logic for this.
        }
    }
}
