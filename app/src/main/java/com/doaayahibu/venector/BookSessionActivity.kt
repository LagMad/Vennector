package com.doaayahibu.venector

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BookSessionActivity : AppCompatActivity() {

    private lateinit var btnConsultation: Button
    private lateinit var btnMentoring: Button
    private lateinit var sessionDetailContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_session_layout)

        btnConsultation = findViewById(R.id.btn_consultation)
        btnMentoring = findViewById(R.id.btn_mentoring)
        sessionDetailContainer = findViewById(R.id.session_detail_container)

        setupListeners()
    }

    private fun setupListeners() {
        btnConsultation.setOnClickListener {
            showConsultationLayout()
        }

        btnMentoring.setOnClickListener {
            showMentoringLayout()
        }
    }

    private fun showConsultationLayout() {
        // Clear existing views
        sessionDetailContainer.visibility = View.VISIBLE
        sessionDetailContainer.removeAllViews()

        // Inflate consultation layout
        val consultationLayout = layoutInflater.inflate(R.layout.book_consultation_layout, sessionDetailContainer, false)
        sessionDetailContainer.addView(consultationLayout)

        // Additional logic for consultation layout can be added here
    }

    private fun showMentoringLayout() {
        // Clear existing views
        sessionDetailContainer.visibility = View.VISIBLE
        sessionDetailContainer.removeAllViews()

        // Inflate mentoring layout
        val mentoringLayout = layoutInflater.inflate(R.layout.book_mentoring_layout, sessionDetailContainer, false)
        sessionDetailContainer.addView(mentoringLayout)

        // Additional logic for mentoring layout can be added here
    }
}
