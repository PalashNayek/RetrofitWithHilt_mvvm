package com.palash.retrofitwithhilt_mvvm.models.limit_skip_product

data class LimitAndSkipProductResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)