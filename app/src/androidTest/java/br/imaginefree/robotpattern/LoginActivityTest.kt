package br.imaginefree.robotpattern

import androidx.test.espresso.intent.rule.IntentsTestRule
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
        mockHomeActivity {  } act { } assert {
            checkTextOnEmailField("")
            checkTextOnPasswordField("")
        }
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmptyEmailError(){
        mockHomeActivity {  } act {
            typePassword("@!56Abuhf")
            login()
        } assert {
            checkMessageShown("E-mail field is Empty!")
        }
    }

    @Test
    fun givenPasswordIsEmpty_whenLogin_shouldShowPasswordEmailError(){
        mockHomeActivity {  } act {
            typeEmail("daivid.v.leal@gmail.com")
            login()
        } assert {
            checkMessageShown("Password field is Empty!")
        }
    }

    @Test
    fun givenPasswordIsInvalid_whenLogin_shouldShowPasswordIsInvalidError(){
        mockHomeActivity {  } act {
            typeEmail("daivid.v.leal@gmail.com")
            typePassword("as#5")
            login()
        } assert {
            checkMessageShown("Password is Invalid!")
        }
    }

    @Test
    fun givenValidEmailAndPassword_whenLogin_shouldGoToHomeActivity(){
        mockHomeActivity {
            mockGoToHomeActivity()
        } act {
            typeEmail("daivid.v.leal@gmail.com")
            typePassword("ashfhd!@$#dsaSDFG$#5")
            login()
        } assert {
            checkGoToHomeActivity()
        }
    }

}
