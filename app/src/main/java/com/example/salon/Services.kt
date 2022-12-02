package com.example.salon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Services : AppCompatActivity() {
    val SERVICE = "MESSAGE"
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        val recyclerViewServices: RecyclerView = findViewById(R.id.services)
        recyclerViewServices.layoutManager = LinearLayoutManager(this)
        recyclerViewServices.adapter = ServiceAdapter(getListServices())

        val recyclerViewCategories: RecyclerView = findViewById(R.id.categories)
        recyclerViewCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategories.adapter = CategoryAdapter(getListCategories())
    }
    private fun getListCategories(): List<Category>{
        val namesList = resources.getStringArray(R.array.categories).toMutableList()
        val categories = mutableListOf<Category>()
        for (name in namesList) {
            categories.add(Category(name, name))
        }
        return categories
    }

    private fun getListServices(): List<String>{
        val services = resources.getStringArray(R.array.services).toMutableList()
        for (str: String in services) {
            print(str)
        }
        return services
    }
    fun onClick(v: View) {
        when (v.id) {
            R.id.button_home_bottom -> startActivity(Intent(this, MainActivity::class.java))
            R.id.button_services_bottom -> startActivity(Intent(this, Services::class.java))
            R.id.button_masters_bottom -> startActivity(Intent(this, Masters::class.java))
            R.id.button_appointments_bottom -> startActivity(Intent(this, Appointments::class.java))
            R.id.button_profile_bottom -> startActivity(Intent(this, Profile::class.java))
        }
    }
}