package com.example.groceryremainderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.groceryremainderapp.data.Grocery_Entity
import com.example.groceryremainderapp.groceryRepository.GroceryViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_create.view.*



class CreateFragment : Fragment() {
private lateinit var groceryViewModel: GroceryViewModel
private lateinit var name : String
private lateinit var quantity : String
private lateinit var price : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create, container, false)

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        view.close.setOnClickListener {
            findNavController().navigate(R.id.action_createFragment_to_homeFragment)
        }

        view.createBtn.setOnClickListener {
            name = item_name_ET.text.toString()
            quantity = item_quantity_ET.text.toString()
            price = item_price_ET.text.toString()

            if (!validateInput(name) || !validateInput(quantity) || !validateInput(price)) {
                Toast.makeText(requireContext(),"Please fill out all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        val data = Grocery_Entity(
            0,
            name,quantity,price
        )

        groceryViewModel.insertItem(data)
        findNavController().navigate(R.id.action_createFragment_to_homeFragment)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
    }

    private fun validateInput(item :String):Boolean{
        if (item.isEmpty()){
            return false
        }
        return true
    }


}