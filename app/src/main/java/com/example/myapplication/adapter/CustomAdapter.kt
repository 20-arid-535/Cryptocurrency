package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CryptoModel

class CustomAdapter(val data:List<CryptoModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtsymbol: TextView = itemView.findViewById(R.id.symbolTxt)
        val txtname: TextView = itemView.findViewById(R.id.nameTxt)
        val txtprice: TextView = itemView.findViewById(R.id.amountTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = data[position]

        // sets the image to the imageview from our itemHolder class
        holder.txtname.text = ItemsViewModel.name
        holder.txtsymbol.text = ItemsViewModel.Symbol
        // sets the text to the textview from our itemHolder class
        holder.txtprice.text = ItemsViewModel.price
    }
}