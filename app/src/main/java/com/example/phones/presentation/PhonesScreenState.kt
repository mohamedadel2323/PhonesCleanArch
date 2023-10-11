package com.example.phones.presentation

import com.example.phones.presentation.models.PhoneUiModel

data class PhonesScreenState(
    val phones: List<PhoneUiModel> = listOf(),
    val loading: Boolean = false,
    val error: String = ""
)
