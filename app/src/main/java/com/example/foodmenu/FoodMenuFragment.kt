package com.example.foodmenu

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmenu.Database.foodTable
import kotlinx.android.synthetic.main.fragment_food_menu.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FoodMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodMenuFragment : Fragment() {
     private lateinit var  foodViewModel:FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_menu, container, false)
        //Introduce recycler view
        val adapter = FoodAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)



        //FoodViewModel
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        foodViewModel.getAllFood.observe(viewLifecycleOwner, Observer { FoodTable ->
            adapter.setData(FoodTable)
        })


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_foodMenuFragment_to_addMenuFragment)
        }

        //add menu
        setHasOptionsMenu(true)
        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenu)
            deleteAllFood()

        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllFood(){
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete everything ")
            .setMessage("Do you want to delete everything ")
            .setPositiveButton("Yes"){_,_ ->
            foodViewModel.delete_AllFood()
            Toast.makeText(requireContext(),"Deleted all",Toast.LENGTH_LONG).show()
        }
            .setNegativeButton("No") { _,_ ->}
        builder.create().show()

    }


    }
