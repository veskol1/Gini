package com.example.ginitask.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ginitask.databinding.ItemOrangeBinding
import com.example.ginitask.databinding.ItemRedBinding

sealed class GiniNumberViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class RedNumberViewHolder(private val binding: ItemRedBinding): GiniNumberViewHolder(binding) {
        fun bind(number: GiniNumberViewItem.RedItemView) {
            binding.textNumber.text = number.value.toString()
        }
    }

    class OrangeNumberViewHolder(private val binding: ItemOrangeBinding): GiniNumberViewHolder(binding) {
        fun bind(number: GiniNumberViewItem.OrangeItemView) {
            binding.textNumber.text = number.value.toString()
        }
    }
}