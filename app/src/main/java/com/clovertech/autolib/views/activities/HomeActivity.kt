package com.clovertech.autolib.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.ActivityHomeBinding
import com.clovertech.autolib.utils.PrefUtils
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp

class HomeActivity : AppCompatActivity(){

    private lateinit var binding: ActivityHomeBinding

    private lateinit var navigationView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = PrefUtils.with(this).getString(PrefUtils.Keys.TOKEN, "")
        if (token.isNullOrEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        FirebaseApp.initializeApp(this)

        //setting up the toolbar is like this
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        this.setSupportActionBar(toolbar)

        //set up the navView
        navigationView = binding.navView

        val drawer = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_notifications,
            R.id.nav_calendar,
            R.id.nav_panne,
            R.id.nav_settings,
            R.id.nav_profile),drawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        /*navigationView.setNavigationItemSelectedListener {
            if(it.itemId == R.id.nav_logout){
                PrefUtils.with(this).save(PrefUtils.Keys.TOKEN, "")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            return@setNavigationItemSelectedListener true
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment)
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}
