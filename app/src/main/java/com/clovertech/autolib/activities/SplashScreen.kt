package com.clovertech.autolib.activities


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.clovertech.autolib.R
import com.clovertech.autolib.ui.adapters.OnBoardingScreens
import com.clovertech.autolib.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_theme.*


class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT :Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        if (PrefUtils.with(this).getString(PrefUtils.Keys.dark,"")=="dark"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            if(PrefUtils.with(this).getString(PrefUtils.Keys.dark,"")=="light"){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                // Start your app main activity
                val i = Intent(this@SplashScreen, OnBoardingScreens::class.java)
                startActivity(i)

                // close this activity
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}
