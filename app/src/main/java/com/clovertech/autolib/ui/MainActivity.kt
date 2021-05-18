package com.clovertech.autolib.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.clovertech.autolib.R
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.viewmodel.TacheViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var tacheViewModel: TacheViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tacheViewModel = ViewModelProvider(this).get(TacheViewModel::class.java)
        var text = findViewById<TextView>(R.id.text)
        val settings = getSharedPreferences(
            "mysettings",
            Context.MODE_PRIVATE
        )
        val mail = settings.getString("email", "")
        if (mail == "") {
            //return to login page
            //Not now
        }


        val tache1 = Tache(1, 1, "this task", 2, 5)
        val tache2 = Tache(2, 5, "this task2", 7, 5)
        tacheViewModel.insertTache(this, tache1)
        tacheViewModel.insertTache(this, tache2)
        tacheViewModel.getAllTaches(this)?.observe(this, Observer {
            text.text = it.toString()
        })


    }


}