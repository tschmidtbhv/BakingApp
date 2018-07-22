package de.naturalsoft.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.naturalsoft.bakingapp.ui.detail.DetailActivity;
import de.naturalsoft.bakingapp.utils.AppConfig;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * BackingApp
 * Created by Thomas Schmidt on 20.07.2018.
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private static final int STEPNUMBER = 2;

    @Rule
    public ActivityTestRule<DetailActivity> mActivityActivityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class){

        @Override
        protected Intent getActivityIntent() {

            Context target = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(target, DetailActivity.class);
            intent.putExtra(AppConfig.RECIPEID, STEPNUMBER);
            return intent;
        }
    };


    @Test
    public void clickStepAndCheckDescriptionDisplayed(){

        //Select Step
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        //Check is recipe_description displayed
        onView(withId(R.id.recipe_description)).check(matches(isDisplayed()));

    }
}
