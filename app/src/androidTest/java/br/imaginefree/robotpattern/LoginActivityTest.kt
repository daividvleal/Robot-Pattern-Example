package br.imaginefree.robotpattern

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val loginActivityTest = IntentsTestRule(LoginActivity::class.java)

    @get:Rule
    val disableAnimationRule = DisableAnimationRule()

    @Test
    fun givenInitialState_shouldShowEmailAndPasswordEmpty(){
        onView(withId(R.id.email))
            .check(matches(withText("")))
        onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmptyEmailError(){
        //arrange
        //act
        onView(withId(R.id.password)).perform(typeText("@!56Abuhf"))
        onView(withId(R.id.login)).perform(click())
        //assert
        onView(withText("E-mail field is Empty!")).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordIsEmpty_whenLogin_shouldShowPasswordEmailError(){
        //arrange
        //act
        onView(withId(R.id.email)).perform(typeText("daivid.v.leal@concrete.com.br"))
        onView(withId(R.id.login)).perform(click())
        //assert
        onView(withText("Password field is Empty!")).check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordIsInvalid_whenLogin_shouldShowPasswordIsInvalidError(){
        //arrange
        //act
        onView(withId(R.id.email)).perform(typeText("daivid.v.leal@concrete.com.br"))
        onView(withId(R.id.password)).perform(typeText("@!56Ab"))
        onView(withId(R.id.login)).perform(click())
        //assert
        onView(withText("Password is Invalid!")).check(matches(isDisplayed()))
    }

    @Test
    fun givenValidEmailAndPassword_whenLogin_shouldGoToHomeActivity(){
        //arrange
        intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
        //act
        onView(withId(R.id.email)).perform(typeText("daivid.v.leal@concrete.com.br"))
        onView(withId(R.id.password)).perform(typeText("@!56Ab654"))
        onView(withId(R.id.login)).perform(click())
        //assert
        intended(hasComponent(HomeActivity::class.java.name))
    }

}
