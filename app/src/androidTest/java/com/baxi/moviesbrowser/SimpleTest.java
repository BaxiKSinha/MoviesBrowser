package com.baxi.moviesbrowser;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.baxi.moviesbrowser.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Baxi on 2018/04/27.
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public SimpleTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void test1ChatId() {
        getActivity();
        onView(withId(R.id.rvMovies))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
