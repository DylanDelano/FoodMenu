package com.example.foodmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmenu.Database.foodTable
import kotlinx.android.synthetic.main.custom_xml.view.*

class FoodAdapter:RecyclerView.Adapter<FoodAdapter.foodViewHolder>() {
    private var FoodList = emptyList<foodTable>()
    class foodViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodViewHolder {
        return foodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_xml,parent,false))
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }

    override fun onBindViewHolder(holder: foodViewHolder, position: Int) {
        val currentFood = FoodList[position]
        holder.itemView.food_name.text = currentFood.foodName
        holder.itemView.chef_name.text = currentFood.chefName
        holder.itemView.price_amount.text = currentFood.priceAmount.toString()

        holder.itemView.card_layout.setOnClickListener {
            val action = FoodMenuFragmentDirections.actionFoodMenuFragmentToUpdateFragment(currentFood)
            holder.itemView.findNavController().navigate(action)
        }

    }
    fun setData(FoodTable:List<foodTable>){
        this.FoodList = FoodTable
        notifyDataSetChanged()
    }

}