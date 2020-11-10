package com.ismin.projectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class TownList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_list)
    }

    fun goToCreation(view: View) {

        Toast.makeText(this, "coucou", Toast.LENGTH_SHORT).show()

    }
}