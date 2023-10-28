package com.example.phones.domain.repository

import com.example.phones.utils.Response

interface PhoneRepoInterface {
   suspend fun <T> getAllPhones() : Response<T>
}