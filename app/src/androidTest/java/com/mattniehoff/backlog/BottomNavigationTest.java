package com.mattniehoff.backlog;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class BottomNavigationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldLoadFragmentOnNavigate(){
        // should navigate to statistics
        onView(withId(R.id.navigation_statistics))
                .perform(click());

        onView(withId(R.id.statistics_title_textview))
                .check(matches(isDisplayed()));

        // should navigate to search
        onView(withId(R.id.navigation_search))
                .perform(click());

        onView(withId(R.id.search_title_textview))
                .check(matches(isDisplayed()));

        // should navigate to library
        onView(withId(R.id.navigation_library))
                .perform(click());

        onView(withId(R.id.library_title_textview))
                .check(matches(isDisplayed()));

        // should navigate to statistics
        onView(withId(R.id.navigation_backlog))
                .perform(click());

        onView(withId(R.id.backlog_title_textview))
                .check(matches(isDisplayed()));
    }
}
