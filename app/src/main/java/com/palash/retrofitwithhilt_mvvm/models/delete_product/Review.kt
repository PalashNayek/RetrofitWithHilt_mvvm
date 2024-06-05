package com.palash.retrofitwithhilt_mvvm.models.delete_product

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)