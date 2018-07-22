package de.naturalsoft.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.naturalsoft.bakingapp.ui.list.ListActivity;
import de.naturalsoft.bakingapp.utils.AppConfig;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * BackingApp
 * Created by Thomas Schmidt on 19.07.2018.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule
    public IntentsTestRule<ListActivity> mActivityTestRule = new IntentsTestRule<>(ListActivity.class);


    @Test
    public void clickRecipeOnList_AndOpenDetail() {

        /*
            The first app lunch will load the recipes
            sleep is used to simulate loading time
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        //Select Recipe
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Check has recipeId extra
        intended(allOf(hasExtraWithKey(AppConfig.RECIPEID)));
    }
}
