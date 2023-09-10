package com.top.store.data.repository

import com.top.store.data.mapper.toModel
import com.top.store.data.network.ApiResponseHandler
import com.top.store.data.network.apiservice.CategoryApi
import com.top.store.domain.model.Category
import com.top.store.domain.repository.BaseCategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi
) : BaseCategoryRepository, ApiResponseHandler() {

    override suspend fun getParentCategories(): List<Category> {
        val res = handleApiResponse { categoryApi.getParentCategories() }
        return res.data.toModel()
    }
}