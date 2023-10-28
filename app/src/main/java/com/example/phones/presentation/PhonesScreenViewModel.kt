package com.example.phones.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phones.domain.models.PhoneDomainModel
import com.example.phones.domain.usecase.PhonesUseCase
import com.example.phones.presentation.mappers.toPhonePresentationModel
import com.example.phones.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PhonesScreenViewModel(private val getAllPhonesUseCase: PhonesUseCase) : ViewModel() {
    private val _phonesScreenState = MutableStateFlow(PhonesState.Display())
    val phonesScreenState = _phonesScreenState.asStateFlow()
    private val _errorState : MutableSharedFlow<PhonesState.Error> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    init {
        getAllPhones()
    }

    private fun getAllPhones() {
        _phonesScreenState.update { it.copy(loading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            getAllPhonesUseCase.getAllPhones<List<PhoneDomainModel>>().apply {
                when (this) {
                    is Response.Success -> {
                        _phonesScreenState.update { displayState ->
                            displayState.copy(phones = this.data?.map { it.toPhonePresentationModel() }
                                ?: listOf(), loading = false)
                        }
                    }
                    is Response.Failure -> {
                        _errorState.onStart { this@apply.error?.let { _errorState.emit(PhonesState.Error(it)) } }.launchIn(viewModelScope)
                        _phonesScreenState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}