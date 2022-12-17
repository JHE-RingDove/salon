package com.example.salon

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CreateAppointment : AppCompatActivity() {
    companion object {
        var TIME = "NULL"
        var DATE = "NULL"
        var NAME = "NULL"
        var JOB = "NULL"
        var RATING = 0
        var SERVICE = "NULL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        val string: String? = intent.getStringExtra("SERVICE")
        SERVICE = intent.getStringExtra(ServiceAdapter.SERVICE).toString()
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        val intent = intent
        val service = intent.getStringExtra(ServiceAdapter.SERVICE)

        val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
        recyclerViewMasters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMasters.adapter = MasterAdapter(getListMaster(service!!))

        val recyclerViewTimes: RecyclerView = findViewById(R.id.time)
        recyclerViewTimes.layoutManager = LinearLayoutManager(this)
        recyclerViewTimes.adapter = TimeAdapter(getListTimes("adf"))

        val calendar: CalendarView = findViewById(R.id.calendar)
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val Date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
            DATE = Date
            val recyclerViewTimes: RecyclerView = findViewById(R.id.time)
            recyclerViewTimes.layoutManager = LinearLayoutManager(this)
            recyclerViewTimes.adapter = TimeAdapter(getListTimes(Date))
        }
}
    private fun getListTimes(date: String) :List<String> {
        //todo select ... where date.time = date  //available time
        val times = resources.getStringArray(R.array.times).toMutableList()
        return times
    }
    private fun getListMaster(service: String) :List<Master> {
        val namesList = mutableListOf<String>()

        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT fio, photo, dolzhnost.dolzhnost,  ocenka FROM `sotrudnik` \n" +
                "\tjoin dolzhnost on sotrudnik.id_dolzhnost = dolzhnost.id_dolzhnost\n" +
                "\twhere id_category = (\n" +
                "\tselect id_category from usluga where nazvanie = '$service')\n", null)
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

        //todo select ... where master.service = service
//        val namesList = resources.getStringArray(R.array.master_names).toMutableList()

//        val masters = mutableListOf<Master>()
//        for (name in namesList) {
//            masters.add(Master(name, "name", "Мастер по $service", 5))
//        }
//        print(masters.toString())
        return masters
    }
    private fun createAppointment(master: Master, service: String, date: String, time: String) {
        val name = master.name
        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("insert into zapis VALUES \n" +
                "(1, \"$date\", \"$time\", (SELECT id_usluga from usluga WHERE nazvanie = \"$service\"),\n" +
                "(select id_dolzhnost from sotrudnik where fio = \"$name\"))", null)
        cursor.moveToFirst()

        Appointments.appointmentsList.add(Appointment(master, service, date, time))
    }
    fun onClick(v: View) {
        when (v.id) {
            R.id.button_make_appointment -> {

                createAppointment(Master(NAME, NAME, JOB, RATING), SERVICE, DATE, TIME)
                startActivity(Intent(this, Services::class.java))
//                Toast.makeText(
//                    v.context,
//                    "appointment: $TIME $DATE $NAME",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
            R.id.button_home_bottom -> startActivity(Intent(this, MainActivity::class.java))
            R.id.button_services_bottom -> startActivity(Intent(this, Services::class.java))
            R.id.button_masters_bottom -> startActivity(Intent(this, Masters::class.java))
            R.id.button_appointments_bottom -> startActivity(Intent(this, Appointments::class.java))
            R.id.button_profile_bottom -> startActivity(Intent(this, Profile::class.java))
        }
    }
}