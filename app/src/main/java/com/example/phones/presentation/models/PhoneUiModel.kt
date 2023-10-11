package com.example.phones.presentation.models

import androidx.annotation.StringRes


data class PhoneUiModel(
    val name: String,
    @StringRes
    val brand: Int
)