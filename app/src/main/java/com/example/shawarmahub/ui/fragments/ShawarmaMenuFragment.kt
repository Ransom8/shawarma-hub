package com.example.shawarmahub.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shawarmahub.R
import com.example.shawarmahub.R.id.recyclerView
import com.example.shawarmahub.adapters.Shawarma
import com.example.shawarmahub.adapters.ShawarmaMenuAdapter


class ShawarmaMenuFragment : Fragment() {

    lateinit var adapter: ShawarmaMenuAdapter
    private val recyclerList = mutableListOf(
        Shawarma(R.drawable.shawarma1, R.string.Chicken_Shawarma, "N1200"),
        Shawarma(R.drawable.shawarma2, R.string.Beef_Shawarma, "N1200"),
        Shawarma(R.drawable.shawarma3, R.string.Fish_Shawarma, "N1200"),
        Shawarma(R.drawable.shawarma4, R.string.Vegan_Shawarma, "N1800"),
        Shawarma(R.drawable.shawarma1, R.string.Chicken_Beef_Combo, "N1800"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_shawarma_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShawarmaMenuAdapter(recyclerList)

//        recyclerView.adapter = ShawarmaMenuAdapter(recyclerList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
    }
}