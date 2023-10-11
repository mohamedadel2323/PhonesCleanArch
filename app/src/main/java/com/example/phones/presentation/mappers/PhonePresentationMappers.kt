package com.example.phones.presentation.mappers

import android.util.Log
import com.example.phones.R
import com.example.phones.domain.models.PhoneDomainModel
import com.example.phones.presentation.models.PhoneUiModel
import com.example.phones.presentation.models.PhoneTypeUiModel

fun PhoneDomainModel.toPhonePresentationModel(): PhoneUiModel =
    PhoneUiModel(
        name = this.name,
        brand =
        when (this.brand) {
            PhoneTypeUiModel.IPHONE.value -> R.string.iphone
            PhoneTypeUiModel.SAMSUNG.value -> R.string.samsung
            else -> R.string.unknown
        }
    )
