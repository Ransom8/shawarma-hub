package com.example.shawarmahub.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shawarmahub.R
import com.example.shawarmahub.databinding.CartItemBinding
import com.example.shawarmahub.db.OrderDatabase
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.repository.Repository
import com.example.shawarmahub.ui.viewModel.MainViewModel
import com.example.shawarmahub.ui.viewModel.ViewModelFactory

class CartAdapter(
    var order: MutableList<Order?>, val viewModel: MainViewModel

) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {




    fun setOrders(data: List<Order?>){
        this.order =data as ArrayList<Order?>
        notifyDataSetChanged()
    }
    inner class MyViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(order: Order) {
            binding.apply {
                itemName.text = order.name
                itemPrice.text = order.price.toString()
                itemImage.setImageResource(R.drawable.shawarma1)
                itemQty.text = order.quantity

            }

        }

        val delete = binding.deleteItem


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curOrder = order[position]
        if (curOrder != null) {
            holder.bind(curOrder)
        }

       holder.delete.setOnClickListener {
           order.removeAt(position)
           viewModel.deleteOrder(order.get(position-1)!!)
           this.notifyDataSetChanged()
           Toast.makeText(it.context, "Item deleted", Toast.LENGTH_SHORT).show()
       }

    }

    override fun getItemCount(): Int = order.size

}