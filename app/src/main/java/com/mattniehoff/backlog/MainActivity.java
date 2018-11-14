package com.mattniehoff.backlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mattniehoff.backlog.adapters.GameEntryOnItemClickHandler;
import com.mattniehoff.backlog.fragments.BacklogFragment;
import com.mattniehoff.backlog.fragments.GameDetailFragment;
import com.mattniehoff.backlog.fragments.LibraryFragment;
import com.mattniehoff.backlog.fragments.SearchFragment;
import com.mattniehoff.backlog.fragments.StatisticsFragment;
import com.mattniehoff.backlog.model.igdb.GameDetail;

// Used https://www.youtube.com/watch?v=jpaHMcQDaDg for help setting up bottom navigation
public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, GameEntryOnItemClickHandler {

    public static final String WIDGET_INTENT_ID = "widget_intent_id";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BACKSTACK_TAG = "backstack_tag";

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

    // https://stackoverflow.com/a/7992472/2107568
    private boolean loadFragmentWithBackstack(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(BACKSTACK_TAG)
                    .commit();

            return true;
        }

        return false;
    }

    private void navigateToFirstFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_library);
        } else {
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int gameId = extras.getInt(WIDGET_INTENT_ID);

            Bundle arguments = new Bundle();
            arguments.putInt(GameDetailFragment.GAME_ID, gameId);

            GameDetailFragment fragment = new GameDetailFragment();
            fragment.setArguments(arguments);
            loadFragmentWithBackstack(fragment);
        }
    }

    @Override
    public void onGameItemClick(int gameId) {
        if (gameId == 0) {
            Toast.makeText(this, "No game ID found!", Toast.LENGTH_LONG).show();
            Log.e(TAG, "No game ID found for onGameItemClick");
            return;
        }

        Bundle arguments = new Bundle();
        arguments.putInt(GameDetailFragment.GAME_ID, gameId);

        GameDetailFragment fragment = new GameDetailFragment();
        fragment.setArguments(arguments);
        loadFragmentWithBackstack(fragment);
    }
}
