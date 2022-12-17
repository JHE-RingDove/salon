package com.example.salon

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Comments : AppCompatActivity() {
    companion object {
        val master = "Алла"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        supportActionBar?.hide(); // hide the title bar
//        this.window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val recyclerViewServices: RecyclerView = findViewById(R.id.comments)
        recyclerViewServices.layoutManager = LinearLayoutManager(this)
        recyclerViewServices.adapter = CommentAdapter(getComments(master))
    }
    private fun getComments(master: String): List<String> {
        val comments = mutableListOf<String>()
        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("SELECT text from comment where id_sotrudnik = (select id_sotrudnik FROM sotrudnik where FIO = 'Алла')", null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast()) {
            comments += cursor.getString(0)
            cursor.moveToNext();
        }
        cursor.close();
        print(comments)
        return comments
    }
    fun onClick(v: View) {
        val textView: TextView = findViewById(R.id.addComment)
        val text = textView.text
        textView.text = ""
        val mDBHelper = DatabaseHelper(this)

        mDBHelper.updateDataBase()
        val mDb = mDBHelper.writableDatabase
        val cursor: Cursor = mDb.rawQuery("insert into comment (id_klient, id_sotrudnik, text)\n" +
                "VALUES\n" +
                "(1,(select id_sotrudnik from sotrudnik where fio = \"$master\"),\"TEXT\")", null)
        cursor.moveToFirst()
//        startActivity(Intent(this, MainActivity::class.java))
    }
}