package com.example.foodmenu

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodmenu.Database.foodTable
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.zip.Inflater


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        foodViewModel= ViewModelProvider(this).get(FoodViewModel::class.java)
        view.update_food_name.setText(args.currentMenu.foodName)
        view.update_chef_name.setText(args.currentMenu.chefName)
        view.update_price_Amount.setText(args.currentMenu.priceAmount)

        view.update_food_menu.setOnClickListener{
            updateDataToDatabase()
        }

        //add menu
        setHasOptionsMenu(true)
        return view

    }

    private fun updateDataToDatabase() {
        val foodName = update_food_name.text.toString()
        val chefName = update_chef_name.text.toString()
        val priceAmount = update_price_Amount.text.toString()
        if (inputCheck(foodName, chefName, priceAmount)){
            val update = foodTable(args.currentMenu.id,foodName, chefName, priceAmount)
            foodViewModel.newFood(update)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_foodMenuFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please enter the relevant values",Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(foodName:String, chefName:String, priceAmount:String):Boolean{
        return!(TextUtils.isEmpty(foodName) && TextUtils.isEmpty(chefName) && TextUtils.isEmpty(priceAmount))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenu)
            deleteFood()

        return super.onOptionsItemSelected(item)
    }
    //creates  a notification dialogue whether to delete the current menu or not
    private fun deleteFood(){

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete${args.currentMenu.foodName}")
            .setMessage("Are you sure you want to delete: ${args.currentMenu.foodName}")
            .setPositiveButton("Yes"){_,_  ->
            foodViewModel.delete_food(args.currentMenu)
            Toast.makeText(requireContext(),"Successfully removed",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_foodMenuFragment)
        }
            .setNegativeButton("No"){_,_  ->}
        builder.create().show()

    }


}


