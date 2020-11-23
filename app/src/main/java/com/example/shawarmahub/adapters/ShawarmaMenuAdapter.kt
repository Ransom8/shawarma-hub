package com.example.shawarmahub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shawarmahub.R
import com.example.shawarmahub.db.model.Sharwama

class ShawarmaMenuAdapter(private var shawarmaList: List<Sharwama>, val clickListener: onItemClickListener
): RecyclerView.Adapter<ShawarmaMenuAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val shawarmaImage: ImageView = itemView.findViewById(R.id.shawarmaImage)
        val shawarmaName: TextView = itemView.findViewById(R.id.shawarmaName)
        val price: TextView = itemView.findViewById(R.id.shawarma_price)

        fun initialize(item:Sharwama, action: onItemClickListener, position: Int){
            shawarmaName.text = item.name
            shawarmaImage.setImageResource(item.image)

                itemView.setOnClickListener {
                    action.itemClick(item, adapterPosition)
                }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shawarma_menu_recycler, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.initialize(shawarmaList.get(position), clickListener, position)
    }

    override fun getItemCount() = shawarmaList.size

    interface onItemClickListener{
        fun itemClick(item:Sharwama, position:Int)
    }
}