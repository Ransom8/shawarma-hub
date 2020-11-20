package com.example.shawarmahub.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: Order)

    @Insert
    suspend fun deleteOrder(order: Order)

    @Query("SELECT * FROM `order`")
    fun getOrder():LiveData<Order>

    @Query("SELECT * FROM `order`")
    fun deleteAllOrder(): LiveData<Order>
}