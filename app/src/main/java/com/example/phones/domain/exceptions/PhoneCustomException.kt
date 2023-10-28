package com.example.phones.domain.exceptions

import java.lang.Exception

sealed class PhoneCustomException(
    val errorCode: PhoneErrorCode,
    errorMessage: String
) : Exception(errorMessage) {


    companion object {
        const val NOT_FOUND_MESSAGE = "Not Found"
        const val REQUEST_TIME_OUT_MESSAGE = "Request Timeout"

        fun mapException(phoneCustomExceptions: PhoneCustomException) {
            when (phoneCustomExceptions.errorCode) {
                PhoneErrorCode.NotFound -> { throw NotFoundException() }
                PhoneErrorCode.RequestTimeout -> { throw RequestTimeoutException() }
                PhoneErrorCode.Other -> { throw OtherException(message = phoneCustomExceptions.message.toString()) }
            }
        }
    }

    data class NotFoundException(
        val code: PhoneErrorCode = PhoneErrorCode.NotFound
    ) : PhoneCustomException(code, NOT_FOUND_MESSAGE)

    data class RequestTimeoutException(
        val code: PhoneErrorCode = PhoneErrorCode.RequestTimeout
    ) : PhoneCustomException(code, REQUEST_TIME_OUT_MESSAGE)

    data class OtherException(
        val code: PhoneErrorCode = PhoneErrorCode.Other,
        override val message: String
    ) : PhoneCustomException(code, message)
}