package com.example.shawarmahub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shawarmahub.db.model.Order

@Database(
    entities = [Order::class],
    version = 3
)
abstract class OrderDatabase : RoomDatabase() {

    abstract fun getOrderDao(): OrderDao
    companion object{
        /**Volatile --other threads can see when a thread changes its instance*/
        @Volatile
        private var instance: OrderDatabase? = null
        /**use to synchronise setting the instance--makes sure there is only a single instance of db at once*/
        private val LOCK = Any()

        /**called when ever an instance of the db is created**/
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{instance = it}
        }

        /**create db func*/
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                OrderDatabase::class.java,
                "order.db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}