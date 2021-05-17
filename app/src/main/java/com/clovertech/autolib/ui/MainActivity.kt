package com.clovertech.autolib.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.clovertech.autolib.R
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache


class MainActivity : AppCompatActivity() {
     var  test : String = ""
    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {





        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text = findViewById<TextView>(R.id.text)
        val settings = getSharedPreferences(
            "mysettings",
            Context.MODE_PRIVATE
        )
        val mail = settings.getString("email", "defaultvalue")
        Toast.makeText(this, mail, Toast.LENGTH_SHORT).show()



        val db = Room.databaseBuilder(
            applicationContext,
            AutolibDatabase::class.java, "database-name"
        ).build()


        AsyncTask.execute {
            var tacheList= mutableListOf<Tache>()
            var tache1 : Tache= Tache(0,1,2,"test1",2,2)
            var tache2 : Tache= Tache(0,3,2,"test2",2,2)

            tacheList.add(tache1)
            tacheList.add(tache2)
            val tacheDao=db.tacheDao()
            tacheDao.insertAllTaches(tacheList)
            var testList = mutableListOf<Tache>()
            testList= tacheDao.getAllTaches() as MutableList<Tache>
            println(testList.get(0).description)
            test= testList.get(0).description
            Log.d(TAG, "MyClass.getView() — get item number $test")






        }


    }


}