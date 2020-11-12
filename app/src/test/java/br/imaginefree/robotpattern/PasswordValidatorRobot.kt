package br.imaginefree.robotpattern

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import java.lang.Exception

var passwordValidator: PasswordValidator = PasswordValidator()

fun PasswordValidatorTest.mockPasswordValidator(func: PasswordValidatorRobot.() -> Boolean): PasswordValidatorRobot {
    return PasswordValidatorRobot().apply {
        PasswordValidatorRobot.resultValidation = func()
    }
}

class PasswordValidatorRobot {

    companion object {
        var resultValidation: Boolean? = null
    }

    fun validate(password: String): Boolean = passwordValidator.validate(password)

    infix fun assert(func: PasswordValidatorResultRobot.() -> Unit) =
        PasswordValidatorResultRobot().apply {
            func()
        }
}

class PasswordValidatorResultRobot {

    fun isTrue() {
        PasswordValidatorRobot.resultValidation?.let {
            assertTrue(it)
        } ?: run {
            throw Exception("Result NOT INITIALIZED.")
        }
    }

    fun isFalse() {
        PasswordValidatorRobot.resultValidation?.let {
            assertFalse(it)
        } ?: run {
            throw Exception("Result NOT INITIALIZED.")
        }
    }

}