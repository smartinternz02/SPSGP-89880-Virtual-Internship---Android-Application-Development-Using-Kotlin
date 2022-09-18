package com.example.groceryremainderapp.groceryRepository

import androidx.lifecycle.LiveData
import com.example.groceryremainderapp.data.GroceryDao
import com.example.groceryremainderapp.data.Grocery_Entity

class GroceryRepository (private val groceryDao: GroceryDao){

    val getAllData : LiveData<List<Grocery_Entity>> = groceryDao.getAllItem()

    suspend fun insertItem(groceryEntity: Grocery_Entity){
        groceryDao.insertItem(groceryEntity)
    }

    suspend fun deleteAll(){
        groceryDao.deleteAll()
    }

    suspend fun updateItem(groceryEntity: Grocery_Entity){
        groceryDao.updateItem(groceryEntity)
    }

    suspend fun deleteItem(groceryEntity: Grocery_Entity){
        groceryDao.deleteItem(groceryEntity)
    }
}