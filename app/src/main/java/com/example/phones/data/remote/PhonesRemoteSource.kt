package com.example.phones.data.remote

import com.example.phones.data.dtos.PhoneDTO

interface PhonesRemoteSource {
    suspend fun getAllPhones(): List<PhoneDTO>
}