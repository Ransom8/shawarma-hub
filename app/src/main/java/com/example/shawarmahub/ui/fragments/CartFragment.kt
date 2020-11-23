package com.example.shawarmahub.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shawarmahub.R
import com.example.shawarmahub.databinding.FragmentCartBinding
import com.example.shawarmahub.db.OrderDatabase
import com.example.shawarmahub.adapters.CartAdapter
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.repository.Repository
import com.example.shawarmahub.ui.viewModel.MainViewModel
import com.example.shawarmahub.ui.viewModel.ViewModelFactory


class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : MainViewModel
    lateinit var adapter : CartAdapter
     var orders = mutableListOf<Order>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        /** dao instance**/
        val dao = OrderDatabase.invoke(requireContext()).getOrderDao()

        /** repository instance**/
        val repository = Repository(dao)

        /** viewModel instance**/
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**continue shopping**/
        binding.continueShopping.setOnClickListener {
            findNavController().navigate(R.id.shawarmaMenuFragment)
        }

        val bundle = CartFragmentArgs.fromBundle(requireArguments())
        var data = bundle.order
        orders.add(data)

        /***setup adapter**/
        adapter = CartAdapter(orders)
        binding.cartRv.adapter = adapter

        viewModel.allOrders().observe(viewLifecycleOwner, Observer {
            adapter.setOrders(it)
        })
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())

            /***display total price**/
        viewModel.totalPrice().observe(viewLifecycleOwner, Observer {
            binding.totalPrice.text = it.toString()
        })

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }


    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}