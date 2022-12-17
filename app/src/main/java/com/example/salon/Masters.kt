package com.example.salon

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Masters : AppCompatActivity() {
//    companion object {
//        private fun changeMasters1() {
//            val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
//            recyclerViewMasters.layoutManager = LinearLayoutManager(this)
//            recyclerViewMasters.adapter = MastersAdapter(getListMasters("asf"))
//        }
//    }
//    override fun onResume() {
//        super.onResume()
//        changeMasters()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masters)

        val recyclerViewCategories: RecyclerView = findViewById(R.id.categories)
        recyclerViewCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategories.adapter = CategoryAdapter(getListCategories())
//        recyclerViewCategories.setOnClickListener { view ->
//            Toast.makeText(
//                view.context,
//                "click on item: weqrqwerewqrewq",
//                Toast.LENGTH_SHORT
//            ).show()
//            print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
//            val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
//            recyclerViewMasters.layoutManager = LinearLayoutManager(this)
//            recyclerViewMasters.adapter = MastersAdapter(getListMasters(""))
//        }


        val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
        recyclerViewMasters.layoutManager = LinearLayoutManager(this)
        recyclerViewMasters.adapter = MastersAdapter(getListMasters("Стрижка"))

    }
    private fun changeMasters() {
        val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
        recyclerViewMasters.layoutManager = LinearLayoutManager(this)
        recyclerViewMasters.adapter = MastersAdapter(getListMasters("asf"))
    }
    private fun getListMasters(service: String) :List<Master> {
        val namesList = mutableListOf<String>()

        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT fio, photo, dolzhnost.dolzhnost,  ocenka FROM `sotrudnik` \n" +
                "\tjoin dolzhnost on sotrudnik.id_dolzhnost = dolzhnost.id_dolzhnost\n", null)
//        val cursor: Cursor = mDb.rawQuery("SELECT fio FROM `sotrudnik` where id_category = (\n" +
//                "\tselect id_category from usluga where nazvanie = '$service')\n", null)
        cursor.moveToFirst()
        val masters = mutableListOf<Master>()
        while (!cursor.isAfterLast) {
            masters.add(Master(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3).toInt()))
//            namesList += cursor.getString(0)
            cursor.moveToNext()
        }
        cursor.close();
//        while (!cursor.isAfterLast) {
//            namesList += cursor.getString(0)
//            cursor.moveToNext()
//        }
//        cursor.close();

        //todo select ... where master.service = service
//        val namesList = resources.getStringArray(R.array.master_names).toMutableList()

//        val masters = mutableListOf<Master>()
//        for (name in namesList) {
//            masters.add(Master(name, "name", "Мастер по $service", 5))
//        }
//        print(masters.toString())
        return masters
//        return masters
    }
    private fun getListCategories(): List<Category> {
        val namesList = resources.getStringArray(R.array.categories).toMutableList()
        val categories = mutableListOf<Category>()
        for (name in namesList) {
            categories.add(Category(name, name))
        }
        return categories
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