package com.clovertech.autolib.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.LoginViewModel
import com.clovertech.autolib.viewmodel.ProfilViewModel
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
                //startActivity(Intent(this, SampleActivity::class.java))
                validateLogin()
            }

        }
    }


    fun validateLogin() {

        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        var Email: String = numChasis.text.toString()
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
                                shareToken(content.token, content.id)

                            }

                        } else {
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()

                        }


                    })

                }
            }
        }
    }


    fun shareToken(token: String, idUser: Int) {
        PrefUtils.with(this).save(PrefUtils.Keys.token, token)
        PrefUtils.with(this).save(PrefUtils.Keys.ID, idUser)
        var viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        if (idUser != 0) {
            viewModel.getThisProfil(idUser)
            viewModel.responseProfil.observe(this, Observer {
                if (it.isSuccessful) {
                    Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT)
                        .show()
                    var profil = it.body()
                    if (profil != null) {
                        var name = profil.firstName + " " + profil.lastName
                        PrefUtils.with(this).save(PrefUtils.Keys.nameAgent, name)
                        Toast.makeText(this, name.toString()+"hhhh", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, SampleActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

    }


}


