package com.example.phones.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setBrand")
fun bindBrand(view: TextView, resource: Int) {
    view.text = view.context.getString(resource)
}