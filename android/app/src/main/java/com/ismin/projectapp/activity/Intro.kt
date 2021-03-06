package com.ismin.projectapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownList

class Intro : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()

        val rotate = this.findViewById<ImageView>(R.id.rot)
        rotate.startAnimation(
            AnimationUtils.loadAnimation(this,
                R.anim.rotate
            )
        )

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}