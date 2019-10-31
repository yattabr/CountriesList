package br.com.wobbu.restcountries

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.wobbu.restcountries.view.main.MainActivity
import br.com.wobbu.restcountries.utils.MockServerDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)
    private lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        webServer = MockWebServer()
        webServer.start(8080)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun fetchListOfCountries() {
        webServer.setDispatcher(MockServerDispatcher.RequestDispatcher())
        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText("United Kingdom")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchCountryByName() {
        webServer.setDispatcher(MockServerDispatcher.RequestDispatcher())
        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.edit_search))
            .perform(ViewActions.clearText(), ViewActions.typeText("United"))

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText("United Kingdom of Great Britain and Northern Ireland")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
