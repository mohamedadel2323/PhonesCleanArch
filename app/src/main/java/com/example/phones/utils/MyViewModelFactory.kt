package com.example.phones.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phones.domain.usecase.PhonesUseCase
import com.example.phones.presentation.PhonesScreenViewModel

class MyViewModelFactory(private val getAllPhonesUseCase: PhonesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass::class.java.isInstance(PhonesScreenViewModel::class.java)) {
            PhonesScreenViewModel(getAllPhonesUseCase) as T
        } else { throw IllegalArgumentException("View Model class not found") }
    }
}