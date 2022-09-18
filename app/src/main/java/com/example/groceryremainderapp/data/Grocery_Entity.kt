package com.example.groceryremainderapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "grocery_table")
data class Grocery_Entity (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val quantity : String,
    val price : String,
        ) : Parcelable