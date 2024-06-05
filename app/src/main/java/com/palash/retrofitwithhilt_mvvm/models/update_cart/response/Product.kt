package com.palash.retrofitwithhilt_mvvm.models.update_cart.response

data class Product(
    val discountPercentage: Double,
    val discountedPrice: Int,
    val id: Int,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,
    val title: String,
    val total: Double
)