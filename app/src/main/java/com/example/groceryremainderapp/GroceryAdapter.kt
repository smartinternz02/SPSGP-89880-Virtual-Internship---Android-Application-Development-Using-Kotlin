package com.example.groceryremainderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryremainderapp.data.Grocery_Entity

class GroceryAdapter(private val context: Context) : RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    private var groceryList = emptyList<Grocery_Entity>()

    class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val quantity = itemView.findViewById<TextView>(R.id.item_quantity)
        val price = itemView.findViewById<TextView>(R.id.item_price)
        val total = itemView.findViewById<TextView>(R.id.total_cost)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_view,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val currentData = groceryList[position]
        holder.name.text = context.resources.getString(R.string.item,currentData.name)
        holder.quantity.text = context.resources.getString(R.string.quantity,currentData.quantity)
        holder.price.text = context.resources.getString(R.string.price,currentData.price)
        val total = currentData.quantity.toFloat() * currentData.price.toFloat()
        holder.total.text = context.resources.getString(R.string.total_cost,total.toString())

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentData)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    fun setData(groceryEntity: List<Grocery_Entity>){
        this.groceryList = groceryEntity
        notifyDataSetChanged()
    }
}