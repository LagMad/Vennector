package com.doaayahibu.venector

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

suspend fun getDataFromFirebase(): String {
    val database = FirebaseDatabase.getInstance("https://vennector-default-rtdb.asia-southeast1.firebasedatabase.app//").getReference("message")
    return try {
        val dataSnapshot = database.get().await()
        dataSnapshot.getValue(String::class.java) ?: "No data"
    } catch (e: Exception) {
        "Error loading data: ${e.message}"
    }
}