package com.example.foodmenu.Database

import androidx.lifecycle.LiveData

class foodRepository(private val FoodDao:foodDao) {
    val getAllFood: LiveData<List<foodTable>> = FoodDao.getAllFood()

    fun insertFood(FoodTable:foodTable){
        FoodDao.addFood(FoodTable)
    }
    fun newFood(FoodTable: foodTable){
        FoodDao.updateFood(FoodTable)
    }
    fun delete_food(FoodTable: foodTable){
        FoodDao.deleteFood(FoodTable)
    }
    fun delete_AllFood(){
        FoodDao.deleteAllFood()
    }
}