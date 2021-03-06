package com.example.shawarmahub.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shawarmahub.db.model.Order


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("SELECT * FROM `order`")
    fun getOrder():LiveData<List<Order>>


    @Query("DELETE  FROM `order`")
   suspend fun deleteAllOrder()


    @Query("SELECT SUM(price) FROM `order`")
    fun totalOrder(): LiveData<Int>

    @Query("SELECT SUM(quantity) FROM `order`")
    fun totalQty(): LiveData<Int>


}