package com.example.foodmenu.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface foodDao {
    @Query("SELECT * FROM foodTable ORDER BY id ASC")
    fun getAllFood():LiveData<List<foodTable>>

    @Insert
    fun addFood(FoodTable:foodTable)

    @Delete
    fun deleteFood(FoodTable: foodTable)

    @Query("DELETE FROM foodTable")
    fun deleteAllFood()

    @Update
    fun updateFood(FoodTable: foodTable)
}