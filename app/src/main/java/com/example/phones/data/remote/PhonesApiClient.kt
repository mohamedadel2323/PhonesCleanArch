package com.example.phones.data.remote

import com.example.phones.data.dtos.PhoneDTO
import com.example.phones.utils.Response

class PhonesApiClient : PhonesRemoteSource {

    override suspend fun getAllPhones(): Response<List<PhoneDTO>> {

        val phoneList = mutableListOf(
            PhoneDTO("Iphone 14", "I"),
            PhoneDTO("Iphone 14 pro max", "I"),
            PhoneDTO("Iphone 12", "I"),
            PhoneDTO("Samsung A24", "S"),
            PhoneDTO("Samsung A13", "S"),
            PhoneDTO("Samsung A34", "S"),
            PhoneDTO("Huawei", "H")
        )

        return Response.Success(phoneList)
    }

}