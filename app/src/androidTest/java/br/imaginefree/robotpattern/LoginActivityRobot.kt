package br.imaginefree.robotpattern

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

fun LoginActivityTest.mockHomeActivity(func: LoginRobotArrange.() -> Unit): LoginRobotAct {
    LoginRobotArrange().apply {
        func()
    }
    return LoginRobotAct()
}

class LoginRobotArrange{

    fun mockGoToHomeActivity(){
        Intents.intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
    }

}

class LoginRobotAct {

    private fun typeText(text: String, id: Int) {
        Espresso.onView(ViewMatchers.withId(id))
            .perform(ViewActions.typeText(text))
    }

    fun typeEmail(email: String){
        typeText(email, R.id.email)
    }

    fun typePassword(password: String){
        typeText(password, R.id.password)
    }

    private fun click(id: Int) {
        Espresso.onView(withId(id)).perform(ViewActions.click())
    }

    fun login(){
        click(R.id.login)
    }

    infix fun act(func: LoginRobotAct.() -> Unit) =
        this.apply{
            func()
        }

    infix fun assert(func: LoginRobotAssert.() -> Unit) =
        LoginRobotAssert().apply {
            func()
        }

}

class LoginRobotAssert {
    fun checkMessageShown(message: String) {
        Espresso.onView(ViewMatchers.withText(message))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun checkTextOnEditText(text: String, id: Int){
        Espresso.onView(withId(id))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkTextOnEmailField(text: String){
        checkTextOnEditText(text, R.id.email)
    }

    fun checkTextOnPasswordField(text: String){
        checkTextOnEditText(text, R.id.password)
    }

    private fun checkGoTo(activityName: String) {
        Intents.intended(hasComponent(activityName))
    }

    fun checkGoToHomeActivity(){
        checkGoTo(HomeActivity::class.java.name)
    }
}