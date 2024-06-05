package com.palash.retrofitwithhilt_mvvm.models.add_new_cart

data class AddNewCartRequest(
    val products: Product,
    val userId: Int
)