package com.example.shawarmahub.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel(){


     fun addOrder(order: Order)=viewModelScope.launch {
        repository.addOrder(order)
    }

    fun allOrders() = repository.getAllOrder()

    fun totalPrice() = repository.totalPrice()



}