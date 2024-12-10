package com.doaayahibu.venector

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_layout)

        // Back button functionality
        val backArrow: ImageView = findViewById(R.id.backArrow)
        backArrow.setOnClickListener { finish() }

        // RadioGroup for language selection
        val suggestedLanguages: RadioGroup = findViewById(R.id.suggestedLanguages)
        val otherLanguages: RadioGroup = findViewById(R.id.otherLanguages)

        // Listener for language selection
        val listener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedLanguage = when (checkedId) {
                R.id.rbEnglishUS, R.id.rbOtherEnglishUS -> "en" // English
                R.id.rbIndonesian, R.id.rbOtherIndonesian -> "in" // Indonesian
                R.id.rbJavanese -> "jv" // Javanese
                R.id.rbSundanese -> "su" // Sundanese
                R.id.rbMalay -> "ms" // Malay
                else -> "en"
            }
            setLocale(selectedLanguage)
        }

        suggestedLanguages.setOnCheckedChangeListener(listener)
        otherLanguages.setOnCheckedChangeListener(listener)
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart the activity to apply the changes
        recreate()
    }
}
