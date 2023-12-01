package com.example.phones.domain.exceptions

import android.util.Log
import java.lang.Exception
private const val TAG = "PhoneCustomException"
sealed class PhoneCustomException(
    private val errorMessage: String
) : Exception(errorMessage) {
    companion object {
        fun mapException(errorCode: Int, errorMessage: String) {
            when (errorCode) {
                PhoneErrorCode.NotFound.code -> {
                    Log.e(TAG, "mapException: NotFound", )
                    throw NotFoundException(errorMessage)
                }

                PhoneErrorCode.RequestTimeout.code -> {
                    Log.e(TAG, "mapException: RequestTimeout", )

                    throw RequestTimeoutException(errorMessage)
                }

                else -> {
                    Log.e(TAG, "mapException: Other", )

                    throw OtherException(errorMessage)
                }
            }
        }
    }

    data class NotFoundException(
        val msg: String
    ) : PhoneCustomException(errorMessage = msg)

    data class RequestTimeoutException(
        val msg: String
    ) : PhoneCustomException(errorMessage = msg)

    data class OtherException(
        val msg: String
    ) : PhoneCustomException(errorMessage = msg)
}