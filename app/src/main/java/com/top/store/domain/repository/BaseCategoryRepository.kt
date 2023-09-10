package com.top.store.domain.repository

import com.top.store.domain.model.Category

interface BaseCategoryRepository {
    suspend fun getParentCategories(): List<Category>
}