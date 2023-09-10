package com.top.store.data.mapper

import com.top.store.data.dto.CategoriesDto
import com.top.store.data.dto.CategoryDto
import com.top.store.domain.model.Category

fun CategoryDto.toModel(): Category {
    return Category(
        id = id,
        name = name,
        image = image,
    )
}

fun CategoriesDto.toModel(): List<Category> {
    return this.categories.map { it.toModel() }
}