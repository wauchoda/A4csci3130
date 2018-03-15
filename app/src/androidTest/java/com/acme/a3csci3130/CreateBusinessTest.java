package com.acme.a3csci3130;

/**
 * Created by wauch on 2018-03-14.
 */

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CreateBusinessTest {

    @Rule
    public ActivityTestRule<CreateBusinessActivity> cActivityRule = new ActivityTestRule<>(CreateBusinessActivity.class);
    MyApplicationData appState;
    private CreateBusinessActivity createActivity;


    @Before
    public void init(){
        createActivity = new CreateBusinessActivity();
    }

    @Test
    public void addBusinessCorrect(){
        onView(withId(R.id.business_number)).perform(typeText("123456789"));
        onView(withId(R.id.name)).perform(typeText("Business1"));
        onView(withId(R.id.primary_business)).perform();

        onView(withId(R.id.submitButton)).perform(click());

        //onView(withId(R.id.)).check(matches(withText("Error, incorrect username or password")));


    }
}
