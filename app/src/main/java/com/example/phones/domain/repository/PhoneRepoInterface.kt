package com.example.phones.domain.repository

import com.example.phones.domain.models.PhoneDomainModel
import com.example.phones.utils.Response

interface PhoneRepoInterface {
   suspend fun getAllPhones() : Response<List<PhoneDomainModel>>
}