package br.com.wobbu.restcountries

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.wobbu.restcountries.utils.MockServerDispatcher
import br.com.wobbu.restcountries.view.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    lateinit var scenario: ActivityScenario<MainActivity>
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
        scenario.close()
    }

    @Test
    fun fetchListOfCountries() {
        webServer.setDispatcher(MockServerDispatcher.RequestDispatcher())
        scenario = launchActivity()

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText("Brazil")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchCountryByName() {
        webServer.setDispatcher(MockServerDispatcher.RequestDispatcher())
        scenario = launchActivity()

        Espresso.onView(ViewMatchers.withId(R.id.edit_search))
            .perform(ViewActions.clearText(), ViewActions.typeText("United"))

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText("United Kingdom of Great Britain and Northern Ireland")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun goToDetailActivity() {
        webServer.setDispatcher(MockServerDispatcher.RequestDispatcher())
        scenario = launchActivity()
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText("Brazil")
            )
        ).perform(click())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_country_name),
                ViewMatchers.withText("Country - Brazil")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
