package com.example.foodmenu
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodmenu.Database.foodTable
import kotlinx.android.synthetic.main.fragment_add_menu.*
import kotlinx.android.synthetic.main.fragment_add_menu.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMenuFragment : Fragment() {
    private lateinit var foodViewModel:FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_menu, container, false)
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        view.add_food_menu.setOnClickListener {
            insertDataToDatabase()
        }
        return view

    }

    private fun insertDataToDatabase() {
        val foodName = edit_food_name.text.toString()
        val chefName = edit_chef_name.text.toString()
        val priceAmount = edit_price_Amount.text.toString()
        if (inputCheck(foodName,chefName,priceAmount)){
            val put = foodTable(0,foodName,chefName,priceAmount)
            foodViewModel.insertFood(put)
            Toast.makeText(requireContext(), "Successfully added ", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addMenuFragment_to_foodMenuFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please enter the required details",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(foodName:String, chefName:String, priceAmount: String):Boolean{
        return!(TextUtils.isEmpty(foodName) && TextUtils.isEmpty(chefName) && TextUtils.isEmpty(priceAmount))
    }

    }


