package com.doaayahibu.venector

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.doaayahibu.venector.databinding.SettingProfileLayoutBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class SettingProfileActivity(navController: NavController) : AppCompatActivity() {

    private lateinit var binding: SettingProfileLayoutBinding
    private lateinit var storageReference: StorageReference

    private var isNotificationOn = true
    private var isLightMode = true

    private val selectPhotoLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { uploadPhotoToFirebase(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingProfileLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance().reference.child("profile_photos")

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Open photo picker
        binding.editPhoto.setOnClickListener {
            selectPhotoLauncher.launch("image/*")
        }

        // Navigate to edit profile
        binding.editProfileInformation.setOnClickListener {
            startActivity(Intent(this, EditProfileInformationActivity::class.java))
        }

        // Toggle notifications
        binding.notification.setOnClickListener {
            isNotificationOn = !isNotificationOn
            val status = if (isNotificationOn) "ON" else "OFF"
            binding.notificationStatus.text = status
            Toast.makeText(this, "Notification is now $status", Toast.LENGTH_SHORT).show()
        }

        // Toggle theme
        binding.language.setOnClickListener {
            isLightMode = !isLightMode
            val mode = if (isLightMode) "Light Mode" else "Dark Mode"
            binding.themeStatus.text = mode
            Toast.makeText(this, "Theme changed to $mode", Toast.LENGTH_SHORT).show()
        }

        // Navigate to security settings
        binding.security.setOnClickListener {
            startActivity(Intent(this, SecurityActivity::class.java))
        }

        // Navigate to about us
        binding.aboutUs.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

        // Navigate to privacy policy
        binding.privacyPolicy.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }

        // Navigate to terms and conditions
        binding.termsAndConditions.setOnClickListener {
            startActivity(Intent(this, TermsAndConditionsActivity::class.java))
        }

        // Logout functionality
        binding.logOutButton.setOnClickListener {
            logOutUser()
        }
    }

    private fun uploadPhotoToFirebase(uri: Uri) {
        val photoRef = storageReference.child("${System.currentTimeMillis()}.jpg")
        photoRef.putFile(uri)
            .addOnSuccessListener {
                photoRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    updatePhoto(downloadUri)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to upload photo", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updatePhoto(uri: Uri) {
        Picasso.with(this).load(uri).fit().centerCrop().into(binding.profilePhoto)
        Toast.makeText(this, "Profile photo updated!", Toast.LENGTH_SHORT).show()
    }

    private fun logOutUser() {
        // Example: Clear user session or perform logout actions
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
