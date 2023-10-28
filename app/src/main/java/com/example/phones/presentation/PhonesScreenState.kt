package com.example.phones.presentation

import com.example.phones.presentation.models.PhoneUiModel

sealed class PhonesState {
    data class Display(
        val phones: List<PhoneUiModel> = listOf(),
        val loading: Boolean = false,
    ) : PhonesState()

    data class Error(
        val errorMessage: String = ""
    ) : PhonesState()
}

