package com.example.shawarmahub.repository

import androidx.lifecycle.LiveData
import com.example.shawarmahub.db.Order
import com.example.shawarmahub.db.OrderDao

class Repository(private val dao: OrderDao) {

    suspend fun addOrder(order: Order)= dao.addOrder(order)

    suspend fun deleteOrder(order: Order) = dao.deleteOrder(order)

    fun  getAllOrder(): LiveData<Order> = dao.getOrder()

    fun deleteAllOrder(): LiveData<Order> = dao.deleteAllOrder()
}