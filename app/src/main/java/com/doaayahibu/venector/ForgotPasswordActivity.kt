package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var confirmButton: Button
    private lateinit var createAccountLink: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_layout)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Views initialization
        emailInput = findViewById(R.id.emailInput)
        confirmButton = findViewById(R.id.confirm_button)
        createAccountLink = findViewById(R.id.CreateAccountLink)

        // Initially set the confirm button to disabled
        confirmButton.isEnabled = false

        // Email input text change listener to enable/disable the confirm button
        emailInput.addTextChangedListener {
            val email = emailInput.text.toString().trim()
            confirmButton.isEnabled = isValidEmail(email)
        }

        // OnClickListener for the "remember password" link
        createAccountLink.setOnClickListener {
            navigateToLoginActivity()
        }

        // OnClickListener for the confirm button
        confirmButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            if (isValidEmail(email)) {
                sendPasswordResetEmail(email)
            } else {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to check if email format is valid
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to send password reset email
    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password reset email sent.", Toast.LENGTH_SHORT).show()
                    navigateToEmailConfirmationActivity()
                } else {
                    Toast.makeText(this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Function to navigate to EmailConfirmationActivity
    private fun navigateToEmailConfirmationActivity() {
        val intent = Intent(this, EmailConfirmationActivity::class.java)
        startActivity(intent)
        finish()  // Close this activity
    }

    // Function to navigate back to LoginActivity
    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()  // Close this activity
    }
}
