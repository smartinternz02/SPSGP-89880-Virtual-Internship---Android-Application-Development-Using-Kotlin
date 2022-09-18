package com.example.groceryremainderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.groceryremainderapp.data.Grocery_Entity
import com.example.groceryremainderapp.groceryRepository.GroceryViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_create.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    val args : UpdateFragmentArgs by navArgs()
    private lateinit var groceryViewModel: GroceryViewModel
    private lateinit var name : String
    private lateinit var quantity : String
    private lateinit var price : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.closeUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)

        view.item_name_ET_update.setText(args.groceryItem.name)
        view.item_quantity_ET_update.setText(args.groceryItem.quantity)
        view.item_price_ET_update.setText(args.groceryItem.price)

        view.updateBtn.setOnClickListener {
            name = item_name_ET_update.text.toString()
            quantity = item_quantity_ET_update.text.toString()
            price = item_price_ET_update.text.toString()

            if (!validateInput(name) || !validateInput(quantity) || !validateInput(price)) {
                Toast.makeText(requireContext(),"Please fill out all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            updateDataItem()
        }

        view.deleteBtn.setOnClickListener {
            groceryViewModel.deleteItem(args.groceryItem)
            toHome()
        }

        return view
    }

    private fun updateDataItem() {

        val data = Grocery_Entity(
            args.groceryItem.id,
            name,quantity,price
        )

        groceryViewModel.updateItem(data)
        toHome()
    }

    private fun toHome(){
        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
    }

    private fun validateInput(item :String):Boolean{
        if (item.isEmpty()){
            return false
        }
        return true
    }

}