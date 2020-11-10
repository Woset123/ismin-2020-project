package com.ismin.projectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private val TownListActivityRequestCode = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ShowTownList(view: View) {
        val intent = Intent(this, TownList::class.java)
        startActivityForResult(intent, this.TownListActivityRequestCode)
    }
}