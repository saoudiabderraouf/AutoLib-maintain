package com.clovertech.autolib

import  android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.ui.MainActivity
import com.clovertech.autolib.viewmodel.AgentViewModel

class LoginActivity : AppCompatActivity() {

    val MIN_PASSWD_LENGTH: Int=3


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_agent)



        var button = findViewById<Button>(R.id.login_button)
        var Email = findViewById<EditText>(R.id.email)
        var Password = findViewById<EditText>(R.id.password)

        button.setOnClickListener{
           Validation(Email.text.toString(), Password.text.toString())

        }
    }
    fun Validation(Email: String, Mdp: String){
        val agent: AgentViewModel = AgentViewModel()
        agent.thisAgent.observe(this, Observer<Auth_utilisateur>{result ->
            println(result)
        })
        agent.getAgent(Email)
        val agentTest: Auth_utilisateur? = agent.thisAgent.value

        if (Email== ""){
            Toast.makeText(this, "Entrer l'email", Toast.LENGTH_SHORT).show()
        }
        else {
            if(Mdp==""){
                Toast.makeText(this, "Entrer le mot de passe", Toast.LENGTH_SHORT).show()
            }
            else{
                if(Mdp.length< MIN_PASSWD_LENGTH){
                    Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show()
                }
                else{
                    if (agentTest != null) {
                        if ((Email== agentTest.adresseEmail) && (Mdp == agentTest.motDePasse)){
                            val settings = getSharedPreferences(
                                    "mysettings",
                                    Context.MODE_PRIVATE
                            )

                            val editor = settings.edit()
                            editor.putString("email", Email)
                            editor.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else{
                            Toast.makeText(this, "Mot de passe ou Email incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }

        }

    }
}