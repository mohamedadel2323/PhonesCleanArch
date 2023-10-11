package com.example.phones.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phones.R
import com.example.phones.databinding.PhoneListItemBinding
import com.example.phones.presentation.models.PhoneUiModel

class PhonesAdapter : ListAdapter<PhoneUiModel, RecipeViewHolder>(RecipesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = DataBindingUtil.inflate<PhoneListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.phone_list_item,
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecipeViewHolder(private val binding: PhoneListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(phone: PhoneUiModel) {
        binding.apply {
            this.phone = phone
            executePendingBindings()
        }
    }
}

class RecipesDiffUtil : DiffUtil.ItemCallback<PhoneUiModel>() {
    override fun areItemsTheSame(
        oldItem: PhoneUiModel,
        newItem: PhoneUiModel
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: PhoneUiModel,
        newItem: PhoneUiModel
    ): Boolean {
        return oldItem == newItem
    }
}