package com.doaayahibu.venector

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_layout)

        // Back button functionality
        val backArrow: ImageView = findViewById(R.id.backButton)
        backArrow.setOnClickListener { finish() }

        // Listener for language selection
        val listener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedLanguage = when (checkedId) {
                R.id.rbEnglishUS -> "en" // English
                R.id.rbIndonesia -> "in" // Indonesian
                R.id.rbJavanese -> "jv" // Javanese
                R.id.rbSundanese -> "su" // Sundanese
                R.id.rbMalay -> "ms" // Malay
                else -> "en"
            }
            setLocale(selectedLanguage)
        }

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
