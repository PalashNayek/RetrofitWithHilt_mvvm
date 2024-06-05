package com.palash.retrofitwithhilt_mvvm.models.update_cart.response

data class UpdateCartResponse(
    val discountedTotal: Int,
    val id: Int,
    val products: List<Product>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)