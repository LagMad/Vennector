package com.doaayahibu.venector

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_us_layout) // Pastikan nama file layout sesuai

        // Inisialisasi tombol back
        val backButton: ImageButton = findViewById(R.id.backButton)

        // Set klik listener untuk tombol back
        backButton.setOnClickListener {
            onBackPressed() // Kembali ke halaman sebelumnya
        }
    }

    override fun onBackPressed() {
        super.onBackPressed() // Memanggil fungsi bawaan untuk kembali
        finish() // Tutup activity saat tombol back ditekan
    }
}

