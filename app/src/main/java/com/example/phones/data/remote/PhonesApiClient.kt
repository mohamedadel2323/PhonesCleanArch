package com.example.phones.data.remote

import com.example.phones.data.dtos.PhoneDTO
import com.example.phones.domain.exceptions.PhoneCustomException
import kotlinx.coroutines.delay

class PhonesApiClient : PhonesRemoteSource {

    override suspend fun getAllPhones(): List<PhoneDTO> {

        delay(3000)
        val phoneList = mutableListOf(
            PhoneDTO("Iphone 14", "I"),
            PhoneDTO("Iphone 14 pro max", "I"),
            PhoneDTO("Iphone 12", "I"),
            PhoneDTO("Samsung A24", "S"),
            PhoneDTO("Samsung A13", "S"),
            PhoneDTO("Samsung A34", "S"),
            PhoneDTO("Huawei", "H")
        )

//        try {
//            throw PhoneCustomException.RequestTimeoutException()
//        } catch (e: Exception) {
//            PhoneCustomException.mapException(PhoneCustomException.RequestTimeoutException())
//        }
        return phoneList
    }

}