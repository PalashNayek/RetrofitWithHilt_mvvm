package com.palash.retrofitwithhilt_mvvm.models.seaech_product_response

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)