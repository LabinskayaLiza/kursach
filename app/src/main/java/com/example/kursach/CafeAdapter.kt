package com.example.kursach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CafeAdapter(private val cafeItems: List<Cafe>) : RecyclerView.Adapter<CafeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val im: ImageView = itemView.findViewById(R.id.im)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cafe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cafeItem = cafeItems[position]
        holder.tvTitle.text = cafeItem.name
        holder.tvDescription.text = cafeItem.description
        // Загрузка изображения с помощью Glide или Picasso
        Glide.with(holder.itemView.context)
            .load(cafeItem.image)
            .into(holder.im)
    }
    override fun getItemCount(): Int = cafeItems.size
}