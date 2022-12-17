package com.example.salon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MasterAdapter(private val names: List<Master>) : RecyclerView
.Adapter<MasterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.master_item_image)
        val name: TextView = itemView.findViewById(R.id.master_item_name)
        val job: TextView = itemView.findViewById(R.id.master_item_job)
        val rating: TextView = itemView.findViewById(R.id.master_item_rating)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.masters_appointments_item_layout)
//        val imageWidth = image.width
//        val imageHeight = image.height
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.masters_appointments_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = names[position].name
        viewHolder.job.text = names[position].job
        viewHolder.rating.text = names[position].rating.toString()
        val imgName = "R.drawable.icon_category_hair"
        Picasso.get()
//            .load(imgName)
            .load(R.drawable.icon_master_1)
//                .resize(viewHolder.imageWidth, viewHolder.imageHeight)
            .resize(325,325)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(viewHolder.image)
        viewHolder.constraintLayout.setOnClickListener { view ->
            Toast.makeText(
                view.context,
                "click on item: " + names[position],
                Toast.LENGTH_SHORT
            ).show()
            CreateAppointment.NAME = names[position].name
            CreateAppointment.JOB = names[position].job
            CreateAppointment.RATING = names[position].rating

            print("------------------------------------")
        }

    }

    override fun getItemCount() = names.size
}