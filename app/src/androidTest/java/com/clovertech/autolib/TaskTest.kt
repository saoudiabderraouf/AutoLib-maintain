package com.clovertech.autolib

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.clovertech.autolib.ui.SampleActivity
import com.clovertech.autolib.ui.adapters.MyViewHolder
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class TaskTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<SampleActivity> =
        ActivityScenarioRule(SampleActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.clovertech.autolib", appContext.packageName)
    }

    @Test(expected = PerformException::class)
    fun taskListClick() {
        onView(withId(R.id.tasksRecyclerView))
            .perform(actionOnItemAtPosition<MyViewHolder>(1, click()))
        //Vérification
        onView(withId(R.id.Titre)).check(matches(withText("This is the task Title")))

    }

    @Test(expected = PerformException::class)
    fun test_display_task_detail() {
        onView(withId(R.id.tasksRecyclerView))
            .perform(actionOnItemAtPosition<MyViewHolder>(1, click()))
        onView(withId(R.id.details)).perform(click())
        onView(withId(R.id.titreTask)).check(matches(withText("This is the task Title")))
        pressBack()
        onView(withId(R.id.tasksRecyclerView)).check(matches(isDisplayed()))
    }


}