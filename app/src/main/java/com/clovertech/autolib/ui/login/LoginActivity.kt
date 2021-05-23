package com.clovertech.autolib.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.ui.MainActivity
import com.clovertech.autolib.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login_agent.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    val MIN_PASSWD_LENGTH: Int = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_agent)
        login_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.login_button -> {
                validateLogin()
            }

        }
    }


    fun validateLogin() {

        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        var Email: String = email.text.toString()
        var Mdp: String = password.text.toString()
        viewModel.onLoginButtonClick(Login(Email, Mdp))

        if (Email == "") {
            Toast.makeText(this, "Entrer l'email", Toast.LENGTH_SHORT).show()
        } else {
            if (Mdp == "") {
                Toast.makeText(
                    this, "Entrer le mot de passe",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (Mdp.length < MIN_PASSWD_LENGTH) {
                    Toast.makeText(
                        this, "Mot de passe incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.Response.observe(this, Observer { response ->
                        if (response.isSuccessful) {
                            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                            val content = response.body()
                            if (content != null) {
                                shareToken(content.token)
                                startActivity(Intent(this, MainActivity::class.java))
                            }

                        } else {
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()

                        }


                    })

                }
            }
        }
    }


    fun shareToken(token: String) {

        val settings = getSharedPreferences(
            "mysettings",
            Context.MODE_PRIVATE
        )

        val editor = settings.edit()
        editor.putString("token", token)

        editor.commit()


    }

}


