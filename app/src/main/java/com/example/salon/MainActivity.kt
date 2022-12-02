package com.example.salon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        resources
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.button_home_bottom -> {
//                startActivity(Intent(this, MainActivity::class.java))
            }
            (R.id.button_services) -> {
                startActivity(Intent(this, Services::class.java))
            }
            (R.id.button_masters) -> {
                startActivity(Intent(this, Masters::class.java))
            }
            (R.id.button_appointments) -> {
                startActivity(Intent(this, Appointments::class.java))
            }
            (R.id.button_profile) -> {
                startActivity(Intent(this, Profile::class.java))
            }
            (R.id.button_services_bottom) -> {
                startActivity(Intent(this, Services::class.java))
            }
            (R.id.button_masters_bottom) -> {
                startActivity(Intent(this, Masters::class.java))
            }
            (R.id.button_appointments_bottom) -> {
                startActivity(Intent(this, Appointments::class.java))
            }
            (R.id.button_profile_bottom) -> {
                startActivity(Intent(this, Profile::class.java))
            }
        }
    }
}