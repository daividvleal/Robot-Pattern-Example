package br.imaginefree.robotpattern

import org.junit.Test

class PasswordValidatorTest {

    @Test
    fun givenPasswordShorterThanEight_whenValidate_shouldReturnFalse(){
        mockPasswordValidator {
            validate("@aB12")
        } assert {
            isFalse()
        }
    }

    @Test
    fun givenPasswordWithoutUpperCase_whenValidate_shouldReturnFalse(){
        mockPasswordValidator {
            validate("ab1#oiu3fd")
        } assert {
            isFalse()
        }
    }

    @Test
    fun givenPasswordWithoutLowerCase_whenValidate_shouldReturnFalse(){
        mockPasswordValidator {
            validate("QWWE#@#12133")
        } assert {
            isFalse()
        }
    }

    @Test
    fun givenPasswordWithoutNumber_whenValidate_shouldReturnFalse(){
        mockPasswordValidator {
            validate("abpo#oiufd")
        } assert {
            isFalse()
        }
    }

    @Test
    fun givenPasswordWithoutSpecialChar_whenValidate_shouldReturnFalse(){
        mockPasswordValidator {
            validate("aGG5oiu3fd")
        } assert {
            isFalse()
        }
    }

    @Test
    fun givenCorrectPassword_whenValidate_shouldReturnTrue(){
        mockPasswordValidator {
            validate("Ab12#@aB")
        } assert {
            isTrue()
        }
    }

}