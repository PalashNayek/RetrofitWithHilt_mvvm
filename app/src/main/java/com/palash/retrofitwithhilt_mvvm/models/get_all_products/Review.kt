package com.palash.retrofitwithhilt_mvvm.models.get_all_products

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)