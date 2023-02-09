package com.example.ginitask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ginitask.R
import com.example.ginitask.databinding.ItemOrangeBinding
import com.example.ginitask.databinding.ItemRedBinding
import com.example.ginitask.ui.GiniNumberViewHolder
import com.example.ginitask.ui.GiniNumberViewItem

class GiniAdapter : RecyclerView.Adapter<GiniNumberViewHolder>() {

    var items = listOf<GiniNumberViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiniNumberViewHolder {
        return when(viewType) {
            R.layout.item_red -> GiniNumberViewHolder.RedNumberViewHolder(
                ItemRedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_orange -> GiniNumberViewHolder.OrangeNumberViewHolder(
                ItemOrangeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GiniNumberViewHolder, position: Int) {
        when(holder) {
            is GiniNumberViewHolder.OrangeNumberViewHolder -> holder.bind(items[position] as GiniNumberViewItem.OrangeItemView)
            is GiniNumberViewHolder.RedNumberViewHolder -> holder.bind(items[position] as GiniNumberViewItem.RedItemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is GiniNumberViewItem.RedItemView -> R.layout.item_red
            is GiniNumberViewItem.OrangeItemView -> R.layout.item_orange
        }
    }
}