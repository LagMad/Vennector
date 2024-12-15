package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class EmailConfirmationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var backToLoginButton: ImageView
    private lateinit var resendEmailLink: TextView
    private lateinit var emailAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email_confirmation)

        auth = FirebaseAuth.getInstance()

        // Get the email address from the previous activity
        emailAddress = intent.getStringExtra("EMAIL_ADDRESS") ?: ""

        // Initialize Views
        backToLoginButton = findViewById(R.id.successmessage)
        resendEmailLink = findViewById(R.id.CreateAccountLink)

        // Set listener for back to login button
        backToLoginButton.setOnClickListener {
            navigateToLoginActivity()
        }

        // Set listener for resend email link
        resendEmailLink.setOnClickListener {
            resendPasswordResetEmail()
        }
    }

    // Function to navigate back to LoginActivity
    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Function to resend the password reset email
    private fun resendPasswordResetEmail() {
        if (emailAddress.isNotEmpty()) {
            auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset email sent again.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to resend the email. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Invalid email address.", Toast.LENGTH_SHORT).show()
        }
    }
}

