package com.example.shawarmahub.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "order")
data class Order (
    var name : String = "",
    var image : Int = 0,
    var quantity: String = "",
    var price: Int = 0
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}