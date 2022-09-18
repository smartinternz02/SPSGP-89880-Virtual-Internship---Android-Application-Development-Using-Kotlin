package com.example.groceryremainderapp.groceryRepository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.groceryremainderapp.data.GroceryDatabase
import com.example.groceryremainderapp.data.Grocery_Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    val getAllItem : LiveData<List<Grocery_Entity>>
    private val repository : GroceryRepository

    init {
        val groceryDao = GroceryDatabase.getDatabase(application).groceryDao()
        repository = GroceryRepository(groceryDao)
        getAllItem = repository.getAllData
    }

    fun insertItem(groceryEntity: Grocery_Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItem(groceryEntity)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun updateItem(groceryEntity: Grocery_Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(groceryEntity)
        }
    }

    fun deleteItem(groceryEntity: Grocery_Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(groceryEntity)
        }
    }
}