package com.example.foodmenu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodmenu.Database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application):AndroidViewModel(application) {
    private val FoodRepository:foodRepository
     val getAllFood:LiveData<List<foodTable>>

    init {
        val FoodDao = MenuDatabase.getDatabase(application).FoodDao()
        FoodRepository = foodRepository(FoodDao)
        this.getAllFood = FoodRepository.getAllFood
    }
    fun insertFood(FoodTable:foodTable){
        viewModelScope.launch(Dispatchers.IO){
            FoodRepository.insertFood(FoodTable)
        }
    }
    fun newFood(FoodTable: foodTable){
        viewModelScope.launch(Dispatchers.IO){
            FoodRepository.newFood(FoodTable)
        }
    }
    fun delete_food(FoodTable:foodTable){
        viewModelScope.launch(Dispatchers.IO){
            FoodRepository.delete_food(FoodTable)
        }
    }
    fun delete_AllFood(){
        viewModelScope.launch(Dispatchers.IO){
            FoodRepository.delete_AllFood()
        }
    }

}