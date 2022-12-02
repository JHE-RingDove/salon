package com.example.salon

import android.content.Intent
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
        var MASTER = "NULL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
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
    private fun getListTimes(date: String) :List<String>{
        //todo select ... where date.time = date  //available time
        val times = resources.getStringArray(R.array.times).toMutableList()
        return times
    }
    private fun getListMaster(service: String) :List<Master> {
        //todo select ... where master.service = service
        val namesList = resources.getStringArray(R.array.master_names).toMutableList()
        val masters = mutableListOf<Master>()
        for (name in namesList) {
            masters.add(Master(name, "name", "Мастер по $service", 34))
        }
        print(masters.toString())
        return masters
    }
    private fun createAppointment(time: String, date: String, master: String) {
        //todo
    }
    fun onClick(v: View) {
        when (v.id) {
            R.id.button_make_appointment -> {
                createAppointment(TIME, DATE, MASTER)
                startActivity(Intent(this, Services::class.java))
                Toast.makeText(
                    v.context,
                    "appointment: $TIME $DATE $MASTER",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.button_home_bottom -> startActivity(Intent(this, MainActivity::class.java))
            R.id.button_services_bottom -> startActivity(Intent(this, Services::class.java))
            R.id.button_masters_bottom -> startActivity(Intent(this, Masters::class.java))
            R.id.button_appointments_bottom -> startActivity(Intent(this, Appointments::class.java))
            R.id.button_profile_bottom -> startActivity(Intent(this, Profile::class.java))
        }
    }
}