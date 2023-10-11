package com.example.phones.data.remote

import com.example.phones.data.dtos.PhoneDTO
import com.example.phones.utils.Response

interface PhonesRemoteSource {
    suspend fun getAllPhones(): Response<List<PhoneDTO>>
}