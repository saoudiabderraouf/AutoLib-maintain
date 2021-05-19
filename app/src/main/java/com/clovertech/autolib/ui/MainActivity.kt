package com.clovertech.autolib.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clovertech.autolib.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settings = getSharedPreferences(
            "mysettings",
            Context.MODE_PRIVATE
        )
        val mail = settings.getString("email", "")
        if (mail == "") {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            initBottomBar()
            val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
            this.setSupportActionBar(toolbar);
            toolbar.setTitle("")
            toolbar.setBackgroundColor(getResources().getColor(R.color.dirtyWhite))
            val navView: BottomNavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_notifications,
                    R.id.navigation_calendar,
                    R.id.navigation_userProfil
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_nav_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    private fun initBottomBar() {
        nav_view.enableItemShiftingMode(false)
        nav_view.enableAnimation(true)
    }
}