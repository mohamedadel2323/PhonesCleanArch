package com.example.phones.data.repository

import com.example.phones.data.mappers.toPhoneDomainModel
import com.example.phones.data.remote.PhonesRemoteSource
import com.example.phones.domain.repository.PhoneRepoInterface
import com.example.phones.utils.Response

class RepositoryImp(private val phonesSource: PhonesRemoteSource) : PhoneRepoInterface {
    override suspend fun <T> getAllPhones(): Response<T> {
        return try {
            Response.Success((phonesSource.getAllPhones()).map { it.toPhoneDomainModel() } as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown")
        }
    }
}