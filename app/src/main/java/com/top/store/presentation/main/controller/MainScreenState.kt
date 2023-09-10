package com.top.store.presentation.main.controller

import com.top.store.domain.model.Category

data class MainScreenState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = "",
)
