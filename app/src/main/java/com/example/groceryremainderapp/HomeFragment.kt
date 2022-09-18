package com.example.groceryremainderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryremainderapp.groceryRepository.GroceryViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var groceryViewModel: GroceryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        view.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createFragment)
        }

        view.deleteAll.setOnClickListener {
            Toast.makeText(context,"Delete All successfully",Toast.LENGTH_SHORT).show()
        }


        val adapter = GroceryAdapter(requireContext())
        val recyclerView = view.recycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        groceryViewModel.getAllItem.observe(viewLifecycleOwner, Observer {
            groceryEntity -> adapter.setData(groceryEntity)
        })

        view.deleteAll.setOnClickListener {
            deleteAllData()
        }

        return view
    }

    private fun deleteAllData() {
        groceryViewModel.deleteAll()
        Toast.makeText(
            requireContext(),
            "Successfully removed everything",
            Toast.LENGTH_SHORT).show()
    }

}