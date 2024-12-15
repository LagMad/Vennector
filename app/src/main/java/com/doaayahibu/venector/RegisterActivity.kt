package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var fullNameInput: EditText
    private lateinit var userNameInput: EditText
    private lateinit var phoneNumberInput: EditText
    private lateinit var businessNameInput: EditText
    private lateinit var businessTypeInput: EditText
    private lateinit var businessNumberInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        // Initialize Firebase Authentication and Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize UI components
        fullNameInput = findViewById(R.id.fullNameInput)
        userNameInput = findViewById(R.id.nickNameInput)
        phoneNumberInput = findViewById(R.id.phoneNumberInput)
        businessNameInput = findViewById(R.id.businessNameInput)
        businessTypeInput = findViewById(R.id.businessTypeInput)
        businessNumberInput = findViewById(R.id.businessNumberInput)
        addressInput = findViewById(R.id.addressInput)
        emailInput = findViewById(R.id.emailInput)
        signUpButton = findViewById(R.id.signupbutton)

        // Listen for changes in the form to enable or disable the sign-up button
        val inputs = listOf(fullNameInput, userNameInput, phoneNumberInput, businessNameInput,
            businessTypeInput, businessNumberInput, addressInput, emailInput)

        inputs.forEach { input ->
            input.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    validateForm()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        // Set up sign-up button
        signUpButton.setOnClickListener {
            val fullName = fullNameInput.text.toString().trim()
            val userName = userNameInput.text.toString().trim()
            val phoneNumber = phoneNumberInput.text.toString().trim()
            val businessName = businessNameInput.text.toString().trim()
            val businessType = businessTypeInput.text.toString().trim()
            val businessNumber = businessNumberInput.text.toString().trim()
            val address = addressInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            // Create user in Firebase
            registerUser(fullName, userName, phoneNumber, businessName, businessType, businessNumber, address, email)
        }
    }

    private fun validateForm() {
        val isValid = fullNameInput.text.isNotEmpty() &&
                userNameInput.text.isNotEmpty() &&
                phoneNumberInput.text.isNotEmpty() &&
                businessNameInput.text.isNotEmpty() &&
                businessTypeInput.text.isNotEmpty() &&
                businessNumberInput.text.isNotEmpty() &&
                addressInput.text.isNotEmpty() &&
                emailInput.text.isNotEmpty()

        // Enable or disable the sign-up button
        signUpButton.isEnabled = isValid
        signUpButton.setBackgroundColor(if (isValid) resources.getColor(R.color.dark_blue) else resources.getColor(R.color.light_blue))
    }

    private fun registerUser(fullName: String, userName: String, phoneNumber: String, businessName: String,
                             businessType: String, businessNumber: String, address: String, email: String) {
        auth.createUserWithEmailAndPassword(email, "default_password") // For now using a dummy password
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // After successful authentication, get the current user
                    val user = auth.currentUser
                    user?.let {
                        // Save user data to Firestore
                        val userData = hashMapOf(
                            "fullName" to fullName,
                            "userName" to userName,
                            "phoneNumber" to phoneNumber,
                            "businessName" to businessName,
                            "businessType" to businessType,
                            "businessNumber" to businessNumber,
                            "address" to address,
                            "email" to email
                        )

                        db.collection("users").document(user.uid).set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                                // Navigate to HomePageActivity
                                startActivity(Intent(this, HomePageActivity::class.java))
                                finish()  // Close RegisterActivity
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
