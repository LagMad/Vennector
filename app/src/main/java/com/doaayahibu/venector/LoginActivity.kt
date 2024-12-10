package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.doaayahibu.venector.databinding.LoginLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginLayoutBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        checkInputs()

        // Tambahkan listener untuk ikon visibilitas password
        binding.passwordLayout.setEndIconOnClickListener {
            togglePasswordVisibility()
        }

        // Tombol Sign In
        binding.signInButton.setOnClickListener {
            signIn()
        }

        // Tombol "Forgot your password?"
        binding.forgotpassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        // Tombol "Create" untuk register
        binding.CreateAccountLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Tombol Google
        binding.googleButton.setOnClickListener {
            Toast.makeText(this, "Login with Google feature not opened yet", Toast.LENGTH_SHORT).show()
        }

        // Tombol Facebook
        binding.facebookButton.setOnClickListener {
            Toast.makeText(this, "Feature not opened yet", Toast.LENGTH_SHORT).show()
        }

        // Tombol Twitter
        binding.twitterButton.setOnClickListener {
            Toast.makeText(this, "Feature not opened yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInputs() {
        binding.usernameEditText.addTextChangedListener {
            validateInputs()
        }

        binding.passwordEditText.addTextChangedListener {
            validateInputs()
        }
    }

    private fun validateInputs() {
        val email = binding.usernameEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        binding.signInButton.isEnabled = !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
    }

    private fun signIn() {
        val email = binding.usernameEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Masuk ke aplikasi setelah berhasil login
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // Tampilkan pesan error
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun togglePasswordVisibility() {
        val editText = binding.passwordEditText
        val isPasswordVisible = editText.inputType == 129
        if (isPasswordVisible) {
            binding.passwordLayout.endIconDrawable = getDrawable(R.drawable.eyes_open)
            editText.inputType = 1 // Tampilkan password
        } else {
            binding.passwordLayout.endIconDrawable = getDrawable(R.drawable.eyes_close)
            editText.inputType = 129 // Sembunyikan password
        }
        editText.setSelection(editText.text?.length ?: 0) // Pindahkan kursor ke akhir teks
    }

    fun onForgotPasswordClicked() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun onCreateAccountLinkClicked() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
