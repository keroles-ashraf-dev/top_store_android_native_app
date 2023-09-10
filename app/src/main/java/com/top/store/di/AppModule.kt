package com.top.store.di

import com.top.store.data.network.ApiConstant
import com.top.store.data.network.apiservice.CategoryApi
import com.top.store.data.repository.CategoryRepositoryImpl
import com.top.store.domain.repository.BaseCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun client(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideCategoryApi(client: OkHttpClient): CategoryApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryApi: CategoryApi): BaseCategoryRepository {
        return CategoryRepositoryImpl(categoryApi)
    }
}