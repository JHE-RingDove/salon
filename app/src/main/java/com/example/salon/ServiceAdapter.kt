package com.example.salon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ServiceAdapter(private val names: List<String>) : RecyclerView
.Adapter<ServiceAdapter.ViewHolder>() {

    companion object {
        val SERVICE: String = "MESSAGEFROMSERVICEADAPTER"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.service_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.button.text = names[position]
        viewHolder.button.setOnClickListener { view ->
            Toast.makeText(
                view.context,
                "click on item: " + names[position],
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(viewHolder.button.context, CreateAppointment::class.java)
//            intent.putExtra(CreateAppointment.SERVICE, "names[position]")
            intent.putExtra(SERVICE, names[position])
            viewHolder.button.context.startActivity(intent)
            print("------------------------------------")
        }

    }

    override fun getItemCount() = names.size
}