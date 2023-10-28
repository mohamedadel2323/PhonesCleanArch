package com.example.phones.domain.exceptions

enum class PhoneErrorCode(val code: Int) {
   NotFound(404),RequestTimeout(408),Other(0)
}