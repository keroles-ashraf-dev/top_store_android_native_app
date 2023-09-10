package com.top.store.data.network.apiservice

import com.top.store.data.dto.ApiResponseDto
import com.top.store.data.dto.CategoriesDto
import com.top.store.data.network.ApiConstant
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {

    @GET(ApiConstant.CATEGORY_URI)
    suspend fun getParentCategories(): Response<ApiResponseDto<CategoriesDto>>
}