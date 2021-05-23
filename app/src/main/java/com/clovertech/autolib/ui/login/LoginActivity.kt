package com.clovertech.autolib.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.network.client.AuthApiClient
import com.clovertech.autolib.ui.MainActivity
import com.clovertech.autolib.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    val MIN_PASSWD_LENGTH: Int = 3


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_agent)

        var button = findViewById<Button>(R.id.login_button)
        var Email = findViewById<EditText>(R.id.email)
        var Password = findViewById<EditText>(R.id.password)
        var login = Login(Email.text.toString(), Password.text.toString())



        button.setOnClickListener {
            executeCall(Email.text.toString(), Password.text.toString())


        }
    }

    private fun executeCall(Email: String, Mdp: String){
        CoroutineScope(Dispatchers.Main).launch {

            try {
                val response = AuthApiClient.authApiService.getUserByInfo(Login(Email, Mdp))

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    if (content != null) {
                        Toast.makeText(
                            this@LoginActivity,
                            content.token,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

   /* fun validateLogin(Email: String, Mdp: String) {

        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.onLoginButtonClick(Login(Email, Mdp))
        viewModel.Response.observe(this, Observer { response -> if (response.isSuccessful) {
                        Toast.makeText(this, "SignIn Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        Log.e("Push", response.body()?.token.toString())
                        Log.e("Push", response.body().toString())
                        Log.e("Push", response.code().toString())
                        Log.e("Push", response.message())
                    } else {
                        Toast.makeText(this, "Login failed !!!", Toast.LENGTH_SHORT).show()
                        Log.e("Push", response.body().toString())
                        Log.e("Push", response.code().toString())
                        Log.e("Push", response.message())
                    }


                })

            }
        }

    }*/

    /*fun Validation(Email: String, Mdp: String) {
        val agent: AgentViewModel = AgentViewModel()
        agent.thisAgent.observe(this, Observer<Auth_utilisateur> { result ->
            println(result)
        })
        agent.getAgent(Email)
        val agentTest: Auth_utilisateur? = agent.thisAgent.value

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
                    if (agentTest != null) {
                        if ((Email == agentTest.adresseEmail) && (Mdp == agentTest.motDePasse)) {
                            val settings = getSharedPreferences(
                                "mysettings",
                                Context.MODE_PRIVATE
                            )

                            val editor = settings.edit()
                            editor.putString("email", Email)

                            editor.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Mot de passe ou Email incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }

            }

        }

    }*/


}