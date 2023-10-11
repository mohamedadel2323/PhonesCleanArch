package com.example.phones.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phones.domain.usecase.PhonesUseCase
import com.example.phones.presentation.mappers.toPhonePresentationModel
import com.example.phones.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhonesScreenViewModel(private val getAllPhonesUseCase: PhonesUseCase) : ViewModel() {

    private val _phonesScreenState = MutableLiveData(PhonesScreenState())
    val phonesScreenState: LiveData<PhonesScreenState>
        get() = _phonesScreenState

    init {
        getAllPhones()
    }

    private fun getAllPhones() {
        _phonesScreenState.value = _phonesScreenState.value?.copy(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            getAllPhonesUseCase.getAllPhones().apply {
                when (this) {
                    is Response.Success -> {
                        _phonesScreenState.postValue(
                            _phonesScreenState.value?.copy(
                                phones = this.data?.map { it.toPhonePresentationModel() }
                                    ?: listOf(),
                                loading = false
                            )
                        )
                    }

                    is Response.Failure -> {
                        _phonesScreenState.postValue(
                            _phonesScreenState.value?.copy(
                                error = this.error ?: "Unknown",
                                loading = false
                            )
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}