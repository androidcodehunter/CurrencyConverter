package com.sharif.currencyconverter.ui.adapter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.ui.adapter.TestUtils.withRecyclerView
import org.junit.Test
import org.junit.runner.RunWith
import com.sharif.currencyconverter.ui.home.MainActivity
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class CurrencyRatesAdapterTest{

    @get:Rule
    var activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkIfCurrentFocusedItemMovedToTop(){
        //make a list item focused by writing something on it.
        onView(withRecyclerView(R.id.rvCurrencyRates).atPositionOnView(1, R.id.etCurrencyAmount)).perform(
            clearText(), typeText("1.123"))

        // then verify that the focused item on top and its value matched with provided value 1.123
        onView(withRecyclerView(R.id.rvCurrencyRates).atPositionOnView(0, R.id.etCurrencyAmount)).check(matches(
            withText("1.123")))
    }

}