package com.top.store.data.dto

data class ApiResponseDto<T>(
    val `data`: T,
    val message: String,
    val success: Int
)