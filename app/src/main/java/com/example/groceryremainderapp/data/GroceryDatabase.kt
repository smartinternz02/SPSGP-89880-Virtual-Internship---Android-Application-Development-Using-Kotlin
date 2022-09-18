package com.example.groceryremainderapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Grocery_Entity::class], version = 1)
abstract class GroceryDatabase : RoomDatabase() {

    abstract fun groceryDao() : GroceryDao

    companion object{
        var INSTANCE : GroceryDatabase ?= null

        fun getDatabase(context: Context) : GroceryDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    "grocery_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}