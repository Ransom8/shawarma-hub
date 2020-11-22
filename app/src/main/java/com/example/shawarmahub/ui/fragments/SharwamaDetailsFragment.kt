package com.example.shawarmahub.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shawarmahub.R
import com.example.shawarmahub.databinding.FragmentSharwamaDetailsBinding
import com.example.shawarmahub.db.OrderDatabase
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.repository.Repository
import com.example.shawarmahub.ui.viewModel.MainViewModel
import com.example.shawarmahub.ui.viewModel.ViewModelFactory


class SharwamaDetailsFragment : Fragment() {

    private var _binding : FragmentSharwamaDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : MainViewModel
    lateinit var decreaseBtn :TextView
    lateinit var increaseBtn : TextView
    lateinit var quantity :TextView
    lateinit var price : TextView
    lateinit var sizeTag : TextView
    lateinit var sausageTag : TextView
    lateinit var smallSize: ImageView
    lateinit var mediumSize: ImageView
    lateinit var largeSize: ImageView
    lateinit var oneSausage: ImageView
    lateinit var twoSausages: ImageView
    lateinit var threeSausages: ImageView

    var initialPrice:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSharwamaDetailsBinding.inflate(inflater, container, false)

        /** dao instance**/
        val dao = OrderDatabase.invoke(requireContext()).getOrderDao()

        /** repository instance**/
        val repository = Repository(dao)

        /** viewModel instance**/
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        decreaseBtn = binding.decrease
        increaseBtn = binding.increase
        quantity = binding.quantity
        price = binding.price
        sizeTag = binding.size
        sausageTag = binding.sausageNum
        smallSize = binding.smallSize
        mediumSize = binding.mediumSize
        largeSize = binding.largeSize
        oneSausage = binding.oneSausage
        twoSausages = binding.twoSausages
        threeSausages = binding.threeSausages


        /***make this a const val from the main page***/
        initialPrice = 200


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**decrease quantity**/
        decreaseBtn.setOnClickListener {
            decreaseQuantity()
        }

        /**increase quantity**/
        increaseBtn.setOnClickListener {
            increaseQuantity()
        }

        /**select small**/
        smallSize.setOnClickListener {
            selectOptions(smallSize, mediumSize, largeSize, R.string.small, -200, sizeTag)
            smallSize.setOnClickListener(null)
        }

        /***select medium**/
        mediumSize.setOnClickListener {
            selectOptions(mediumSize, smallSize, largeSize, R.string.medium, 0, sizeTag)
            mediumSize.setOnClickListener(null)
        }

        /***select large**/
        largeSize.setOnClickListener {
            selectOptions(largeSize, smallSize, mediumSize, R.string.large, 200, sizeTag)
            largeSize.setOnClickListener(null)
        }

        /***select one sausage**/
        oneSausage.setOnClickListener {
            selectOptions(oneSausage, twoSausages, threeSausages, R.string.one_, 0, sausageTag)
            oneSausage.setOnClickListener(null)
        }

        /***select two sausage**/
        twoSausages.setOnClickListener {
            selectOptions(twoSausages, oneSausage, threeSausages, R.string.two, 200, sausageTag)
            twoSausages.setOnClickListener(null)
        }

        /***select three sausage**/
        threeSausages.setOnClickListener {
            selectOptions(threeSausages, twoSausages, oneSausage, R.string.three, 300, sausageTag)
            threeSausages.setOnClickListener(null)
        }


        /**add to cart***/
        binding.addToCart.setOnClickListener {
            addOrderToCart()
        }
    }


    private fun decreaseQuantity(){
        var newPrice = initialPrice
        var qty = quantity.text.toString().toInt()

        if(qty == 1) price.text = initialPrice.toString()

        if(qty > 1) {
            qty--
           newPrice *= qty

            quantity.text = qty.toString()
            price.text = newPrice.toString()
        }

    }

    private fun increaseQuantity(){

        var qty = quantity.text.toString().toInt()
        var curPrice = price.text.toString().toInt()
        if(qty >= 1) {
            qty++
            quantity.text = qty.toString()
            curPrice += initialPrice
            price.text = curPrice.toString()
        }

    }

    private fun selectOptions(mainItem:ImageView, item1:ImageView, item2:ImageView, tag: Int, amt:Int, tagView:TextView){
        mainItem.alpha = 1f
        item1.alpha = .4f
        item2.alpha =.4f
        tagView.setText(tag)

        val oldPrice = price.text.toString().toInt()
        val newPrice = oldPrice + amt
        price.text = newPrice.toString()

    }

    private fun addOrderToCart(){
        val order = Order("name", 1, "3", 300)
        viewModel.addOrder(order)
        Toast.makeText(requireContext(), "Order Added To Cart", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.cartFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}