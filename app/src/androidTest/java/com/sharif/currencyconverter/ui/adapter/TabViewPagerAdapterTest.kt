package com.sharif.currencyconverter.ui.adapter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.ui.home.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TabViewPagerAdapterTest {

    @get:Rule
    var activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkTabLayoutDisplayed(){
        onView(withId(R.id.pagerCurrency))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTabSwitchedToCurrencyConverter(){
        onView(allOf(withText("Converter"), isDescendantOfA(withId(R.id.tabLayout))))
            .check(matches(isDisplayed()))
    }

}