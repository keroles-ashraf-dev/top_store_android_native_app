package com.top.store.domain.usecase.base

// P -> params type
// R -> returned type
interface BaseUsecase<R, P> {
    fun invoke(params: P): R
}