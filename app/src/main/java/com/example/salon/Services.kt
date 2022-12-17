package com.example.salon

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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
        val namesList = mutableListOf<String>()

        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT name FROM `category`", null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast()) {
            namesList += cursor.getString(0)
            cursor.moveToNext();
        }
        cursor.close();

        val categories = mutableListOf<Category>()
        for (name in namesList) {
            categories.add(Category(name, name))
        }
        return categories
    }

    private fun getListServices(): List<String>{
        val services = mutableListOf<String>()

        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT nazvanie FROM `usluga`", null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast()) {
            services += cursor.getString(0)
            cursor.moveToNext();
        }
        cursor.close();

//        val services = resources.getStringArray(R.array.services).toMutableList()
//        val services = mutableListOf<Services>()
//        for (name in namesList) {
//            services.add(Service(name, name))
//        }
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