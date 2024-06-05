package com.palash.retrofitwithhilt_mvvm.models.add_new_cart.response

data class AddNewCartResponse(
    val discountedTotal: Int,
    val id: Int,
    val products: List<Product>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)