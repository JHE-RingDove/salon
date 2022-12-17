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

//val appointmentsList = mutableListOf<Appointment>()
class Appointments : AppCompatActivity() {
    companion object {
        var appointmentsList = mutableListOf<Appointment>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val recyclerViewAppointments: RecyclerView = findViewById(R.id.appointments)
        recyclerViewAppointments.layoutManager = LinearLayoutManager(this)
        recyclerViewAppointments.adapter = AppointmentAdapter(getListAppointments())
    }

    private fun getListAppointments() :List<Appointment> {
        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT sotrudnik.fio, \n" +
                "sotrudnik.photo, \n" +
                "dolzhnost.dolzhnost, \n" +
                "sotrudnik.ocenka,\n" +
                "usluga.nazvanie,\n" +
                "data,\n" +
                "vremya\n" +
                "\n" +
                "FROM `zapis`\n" +
                "join sotrudnik on zapis.id_sotrudnik = sotrudnik.id_sotrudnik \n" +
                "join dolzhnost on dolzhnost.id_dolzhnost = sotrudnik.id_dolzhnost\n" +
                "join usluga on usluga.id_usluga = zapis.id_usluga\n", null)
        cursor.moveToFirst()

        val appointments = mutableListOf<Appointment>()
        while (!cursor.isAfterLast) {
            appointments.add(Appointment(Master(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3).toInt()),
                cursor.getString(4), cursor.getString(5), cursor.getString(6)))
            cursor.moveToNext()
        }
        cursor.close();

//        val appointment = Appointment(
//            Master("Нелли", "IMG", "Мастер по маникюру", 5),
//            "Маникюр",
//            "11.12.2022",
//            "12:00"
//        )

//        appointmentsList.add(appointment)

        return appointments
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