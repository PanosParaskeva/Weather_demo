package com.example.panagiotis.weather_demo_app;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MyEspressoTesting extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivity mainActivity;
    public MyEspressoTesting() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception{
        super.setUp();
        mainActivity=getActivity();
    }

    public void testWhiteBox1(){
        onView(withId(R.id.RecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
    }

    private String getString(int resId){
        return getInstrumentation().getTargetContext().getString(resId);
    }
}
