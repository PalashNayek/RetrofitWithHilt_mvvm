package com.palash.retrofitwithhilt_mvvm.models.get_all_products

data class GetAllProducts(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)