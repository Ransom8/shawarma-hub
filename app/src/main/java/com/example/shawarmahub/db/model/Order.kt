package com.example.shawarmahub.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "order")
data class Order (
    var name : String,
    var image : Int,
    var quantity: String,
    var price: Int
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}