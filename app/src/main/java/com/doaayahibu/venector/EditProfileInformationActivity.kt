package com.doaayahibu.venector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.doaayahibu.venector.databinding.EditProfileInformationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileInformationActivity : AppCompatActivity() {

    private lateinit var binding: EditProfileInformationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private var isDataChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditProfileInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val userId = firebaseAuth.currentUser?.uid ?: return

        loadUserData(userId)

        setupListeners()

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.confirmButton.setOnClickListener {
            if (isDataChanged) {
                updateUserProfile(userId)
            }
        }
    }

    private fun loadUserData(userId: String) {
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.fullNameInput.hint = document.getString("full_name")
                    binding.nickNameInput.hint = document.getString("username")
                    binding.phoneNumberInput.hint = document.getString("phone_number")
                    binding.businessNameInput.hint = document.getString("business_name")
                    binding.businessTypeInput.hint = document.getString("business_type")
                    binding.businessNumberInput.hint = document.getString("business_number")
                    binding.addressInput.hint = document.getString("address")
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkIfDataChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.fullNameInput.addTextChangedListener(textWatcher)
        binding.nickNameInput.addTextChangedListener(textWatcher)
        binding.phoneNumberInput.addTextChangedListener(textWatcher)
        binding.businessNameInput.addTextChangedListener(textWatcher)
        binding.businessTypeInput.addTextChangedListener(textWatcher)
        binding.businessNumberInput.addTextChangedListener(textWatcher)
        binding.addressInput.addTextChangedListener(textWatcher)
    }

    private fun checkIfDataChanged() {
        isDataChanged = binding.fullNameInput.text.toString().isNotEmpty() ||
                binding.nickNameInput.text.toString().isNotEmpty() ||
                binding.phoneNumberInput.text.toString().isNotEmpty() ||
                binding.businessNameInput.text.toString().isNotEmpty() ||
                binding.businessTypeInput.text.toString().isNotEmpty() ||
                binding.businessNumberInput.text.toString().isNotEmpty() ||
                binding.addressInput.text.toString().isNotEmpty()

        binding.confirmButton.isEnabled = isDataChanged
    }

    private fun updateUserProfile(userId: String) {
        val updates = hashMapOf<String, Any>()

        if (binding.fullNameInput.text.toString().isNotEmpty()) {
            updates["full_name"] = binding.fullNameInput.text.toString()
        }
        if (binding.nickNameInput.text.toString().isNotEmpty()) {
            updates["username"] = binding.nickNameInput.text.toString()
        }
        if (binding.phoneNumberInput.text.toString().isNotEmpty()) {
            updates["phone_number"] = binding.phoneNumberInput.text.toString()
        }
        if (binding.businessNameInput.text.toString().isNotEmpty()) {
            updates["business_name"] = binding.businessNameInput.text.toString()
        }
        if (binding.businessTypeInput.text.toString().isNotEmpty()) {
            updates["business_type"] = binding.businessTypeInput.text.toString()
        }
        if (binding.businessNumberInput.text.toString().isNotEmpty()) {
            updates["business_number"] = binding.businessNumberInput.text.toString()
        }
        if (binding.addressInput.text.toString().isNotEmpty()) {
            updates["address"] = binding.addressInput.text.toString()
        }

        firestore.collection("users").document(userId).update(updates)
            .addOnSuccessListener {
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
            }
    }
}
