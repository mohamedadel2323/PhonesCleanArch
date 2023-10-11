package com.example.phones.utils

import android.view.View

infix fun View.visibleIf(condition : Boolean){
    visibility = if(condition) View.VISIBLE else View.INVISIBLE
}