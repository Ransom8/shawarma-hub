package com.example.shawarmahub.ui.fragments

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shawarmahub.R
import com.example.shawarmahub.R.id.recyclerView
import com.example.shawarmahub.adapters.Shawarma
import com.example.shawarmahub.adapters.ShawarmaMenuAdapter
import com.example.shawarmahub.databinding.FragmentSharwamaDetailsBinding
import com.example.shawarmahub.databinding.FragmentShawarmaMenuBinding
import com.example.shawarmahub.db.OrderDatabase
import com.example.shawarmahub.db.model.Sharwama
import com.example.shawarmahub.repository.Repository
import com.example.shawarmahub.ui.viewModel.MainViewModel
import com.example.shawarmahub.ui.viewModel.ViewModelFactory


class ShawarmaMenuFragment : Fragment(), ShawarmaMenuAdapter.onItemClickListener {

    private var _binding : FragmentShawarmaMenuBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : MainViewModel

    lateinit var adapter: ShawarmaMenuAdapter
    private val recyclerList = mutableListOf(
        Sharwama(1,  "Chicken Sharwama", R.drawable.shawarma1,"1200"),
        Sharwama(2, "Beef Sharwama", R.drawable.shawarma2,"1200"),
        Sharwama(3,  "Fish Sharwama",R.drawable.shawarma3, "1200"),
        Sharwama(4,  "Vegan Sharwama",R.drawable.shawarma4, "1800"),
        Sharwama(5,  "Chicken Beef Combo",R.drawable.shawarma1, "1800"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentShawarmaMenuBinding.inflate(inflater, container, false)

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

        viewModel.totalQty().observe(viewLifecycleOwner, Observer {
            binding.textView5.text = it.toString()
        })

        adapter = ShawarmaMenuAdapter(recyclerList, this)

        binding.recyclerView.adapter = ShawarmaMenuAdapter(recyclerList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }
    }

    override fun itemClick(item: Sharwama, position: Int) {
       val action = ShawarmaMenuFragmentDirections.actionShawarmaMenuFragmentToSharwamaDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}