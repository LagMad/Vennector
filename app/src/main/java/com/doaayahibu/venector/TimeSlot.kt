package com.doaayahibu.venector.models

data class TimeSlot(
    val day: String,
    val time: String,
    val isAvailable: Boolean,
    var isSelected: Boolean = false
)
