package com.example.salon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private val names: List<String>) : RecyclerView
.Adapter<CommentAdapter.ViewHolder>() {

//    companion object {
//        val SERVICE: String = "MESSAGEFROMSERVICEADAPTER"
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.comment_item)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.comment_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = names[position]


    }

    override fun getItemCount() = names.size
}