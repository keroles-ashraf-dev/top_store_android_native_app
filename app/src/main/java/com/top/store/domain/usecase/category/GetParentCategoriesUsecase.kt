package com.top.store.domain.usecase.category

import android.util.Log
import com.top.store.core.error.LocalException
import com.top.store.core.error.ServerException
import com.top.store.core.util.Resource
import com.top.store.domain.model.Category
import com.top.store.domain.model.NoParams
import com.top.store.domain.repository.BaseCategoryRepository
import com.top.store.domain.usecase.base.BaseUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetParentCategoriesUsecase @Inject constructor(
    private val categoryRepository: BaseCategoryRepository
) : BaseUsecase<Flow<Resource<List<Category>>>, NoParams> {
    override operator fun invoke(params: NoParams): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())

            val categories = categoryRepository.getParentCategories()

            emit(Resource.Success(data = categories))
        } catch (e: Exception) {
            when(e){
                is ServerException -> emit(Resource.Error(message = e.message))
                is LocalException -> emit(Resource.Error(message = e.message))
                else -> emit(Resource.Error(message = e.message))
            }

        }
    }


}