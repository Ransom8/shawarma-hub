package com.example.shawarmahub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shawarmahub.R

class ShawarmaMenuAdapter(private var shawarmaList: List<Shawarma>): RecyclerView.Adapter<ShawarmaMenuAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val shawarmaImage: ImageView = itemView.findViewById(R.id.shawarmaImage)
        val shawarmaName: TextView = itemView.findViewById(R.id.shawarmaName)
        val price: TextView = itemView.findViewById(R.id.shawarma_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shawarma_menu_recycler, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = shawarmaList[position]
        holder.shawarmaImage.setImageResource(currentItem.shawarmaImage)
        holder.shawarmaName.text = currentItem.shawarmaName.toString()
        holder.shawarmaName.text = currentItem.price
    }

    override fun getItemCount() = shawarmaList.size
}