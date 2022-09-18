package com.example.groceryremainderapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(groceryEntity: Grocery_Entity)

    @Query("SELECT * FROM grocery_table")
    fun getAllItem() : LiveData<List<Grocery_Entity>>

    @Query("DELETE FROM grocery_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateItem(groceryEntity: Grocery_Entity)

    @Delete
    suspend fun deleteItem(groceryEntity: Grocery_Entity)
}