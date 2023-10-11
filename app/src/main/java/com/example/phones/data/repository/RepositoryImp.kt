package com.example.phones.data.repository

import com.example.phones.data.dtos.PhoneDTO
import com.example.phones.data.mappers.toPhoneDomainModel
import com.example.phones.data.remote.PhonesRemoteSource
import com.example.phones.domain.models.PhoneDomainModel
import com.example.phones.domain.repository.PhoneRepoInterface
import com.example.phones.utils.Response

class RepositoryImp(private val phonesSource: PhonesRemoteSource) : PhoneRepoInterface {
    override suspend fun getAllPhones(): Response<List<PhoneDomainModel>> {
        return try {
            Response.Success((phonesSource.getAllPhones().data as List<PhoneDTO>).map { it.toPhoneDomainModel() })
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown")
        }
    }
}