package com.example.shawarmahub.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "order")
data class Order (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var item : String,
    var image : Int,
    var quantity: Int,
    var size: Int,
    var sausage: Int

): Serializable