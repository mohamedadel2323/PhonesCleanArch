package com.example.phones.domain.usecase

import com.example.phones.domain.models.PhoneDomainModel
import com.example.phones.domain.repository.PhoneRepoInterface
import com.example.phones.utils.Response

class PhonesUseCase(private val repository: PhoneRepoInterface) {
    suspend  fun getAllPhones(): Response<List<PhoneDomainModel>> = repository.getAllPhones()

}