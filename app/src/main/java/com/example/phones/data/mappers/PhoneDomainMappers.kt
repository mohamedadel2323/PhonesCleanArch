package com.example.phones.data.mappers

import com.example.phones.data.dtos.PhoneDTO
import com.example.phones.domain.models.PhoneDomainModel

fun PhoneDTO.toPhoneDomainModel(): PhoneDomainModel = PhoneDomainModel(
    name = this.name ?: "",
    brand = this.brand ?: ""
)