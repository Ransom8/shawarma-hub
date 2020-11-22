package com.example.shawarmahub.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shawarmahub.R

class ShawarmaMenuAdapter(private val shawarmaList: List<Shawarma>): RecyclerView.Adapter<ShawarmaMenuAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val shawarmaImage: ImageView = itemView.findViewById(R.id.shawarmaImage)
        val shawarmaName: TextView = itemView.findViewById(R.id.shawarmaName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}