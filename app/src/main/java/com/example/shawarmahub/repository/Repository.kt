package com.example.shawarmahub.repository

import androidx.lifecycle.LiveData
import com.example.shawarmahub.db.model.Order
import com.example.shawarmahub.db.OrderDao

class Repository(private val dao: OrderDao) {

    suspend fun addOrder(order: Order)= dao.addOrder(order)

    suspend fun deleteOrder(order: Order) = dao.deleteOrder(order)

    fun  getAllOrder() = dao.getOrder()

    fun deleteAllOrder() = dao.deleteAllOrder()

    fun totalPrice() = dao.totalOrder()
}