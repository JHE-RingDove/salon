package com.example.salon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryAdapter(private val names: List<Category>) : RecyclerView
.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.category_item_image)
        val text: TextView = itemView.findViewById(R.id.category_item_text)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.category_item_layout)
//        val imageWidth = image.width
//        val imageHeight = image.height
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.text.text = names[position].name
//        val imageWidth = viewHolder.image.width
//        val imageHeight = viewHolder.image.height
        val imgName = "R.drawable.icon_category_hair"
        Picasso.get()
//            .load(imgName)
            .load(R.drawable.icon_category_hair)
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
            print("------------------------------------")
//            val recyclerViewMasters: RecyclerView = findViewById(R.id.masters)
//            recyclerViewMasters.layoutManager = LinearLayoutManager(this)
//            recyclerViewMasters.adapter = MastersAdapter(getListMasters("asf"))
        }

    }

    override fun getItemCount() = names.size
}