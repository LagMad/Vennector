package com.doaayahibu.venector

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.doaayahibu.venector.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_security.*

class SecurityActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_layout)

        auth = FirebaseAuth.getInstance()

        // Ambil email dan password dari EditText
        val emailInput = emailInput.text.toString().trim()
        val passwordInput = passwordName.text.toString().trim()

        // Mengubah email pengguna
        EmailChangeButton.setOnClickListener {
            auth.currentUser?.updateEmail(emailInput)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to update email", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Mengubah password pengguna
        passwordChangeButton.setOnClickListener {
            if (passwordInput.length >= 6) { // pastikan password memiliki setidaknya 6 karakter
                auth.currentUser?.updatePassword(passwordInput)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
