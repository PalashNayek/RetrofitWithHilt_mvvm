package com.palash.retrofitwithhilt_mvvm.models.update_cart.request

data class UpdateCartRequest(
    val merge: Boolean,
    val products: List<Product>
)