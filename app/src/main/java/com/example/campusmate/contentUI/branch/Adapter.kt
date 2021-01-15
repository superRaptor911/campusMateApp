package com.example.campusmate.contentUI.branch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusmate.R

class Adapter (private  val context : Context,
               private val dataset : List<Item>) : RecyclerView.Adapter<Adapter.ItemViewHolder>(){

    class ItemViewHolder(private  val view : View) : RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.recyclerItem_text_view)
        val card : CardView = view.findViewById(R.id.recyclerItem_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = context.resources.getString(dataset[position].name_id)
        holder.card.setOnClickListener {
        }
    }
}
