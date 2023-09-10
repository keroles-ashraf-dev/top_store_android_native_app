package com.top.store.presentation.main.controller

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top.store.core.util.Resource
import com.top.store.domain.model.NoParams
import com.top.store.domain.usecase.category.GetParentCategoriesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getParentCategoriesUsecase: GetParentCategoriesUsecase
) : ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    init {
        getParentCategories()
    }

    private fun getParentCategories() {
        getParentCategoriesUsecase(NoParams).onEach { res ->
            when (res) {
                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MainScreenState(categories = res.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MainScreenState(error = res.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}