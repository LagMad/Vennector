package com.doaayahibu.venector

data class Mentor(
    val name: String,
    val position: String,
    val photo: Int,
    val rating: Float,
    val skills: List<String>,
    var isFavorite: Boolean
)
