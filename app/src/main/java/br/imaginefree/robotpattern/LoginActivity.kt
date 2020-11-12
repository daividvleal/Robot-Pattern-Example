package br.imaginefree.robotpattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val passwordValidator = PasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            when {
                email.text.isEmpty() -> {
                    showError(R.string.email_empty)
                }
                password.text.isEmpty() -> {
                    showError(R.string.password_empty)
                }
                !passwordValidator.validate(password.text.toString()) -> {
                    showError(R.string.password_invalid)
                }
                else -> {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                }
            }
        }
    }

    private fun showError(@StringRes error: Int){
        AlertDialog.Builder(this)
            .setMessage(error).show()
    }

}