package com.palash.retrofitwithhilt_mvvm.models.seaech_product_response

data class ProductSearchResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)