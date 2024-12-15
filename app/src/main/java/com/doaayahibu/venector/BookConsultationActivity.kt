package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doaayahibu.venector.adapters.TimeSlotAdapter
import com.doaayahibu.venector.models.TimeSlot

class BookConsultationActivity : AppCompatActivity() {

    private lateinit var findNewMentorButton: Button
    private lateinit var orderNowButton: Button
    private lateinit var timeSlotRecyclerView: RecyclerView
    private val timeSlots = mutableListOf<TimeSlot>()
    private var selectedTimeSlot: TimeSlot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_consultation_layout)
        setContentView(R.layout.book_session_layout)

        // Initialize views
        findNewMentorButton = findViewById(R.id.find_new_mentor_button)
        orderNowButton = findViewById(R.id.order_now_button)
        timeSlotRecyclerView = findViewById(R.id.time_slots_container_consultation)

        // Set up listeners
        findNewMentorButton.setOnClickListener { navigateToMentorSelection() }
        orderNowButton.setOnClickListener { processOrder() }

        // Initialize time slots
        initializeTimeSlots()
        setupTimeSlotRecyclerView()
    }

    private fun initializeTimeSlots() {
        val times = listOf("9:00 AM", "9:30 AM", "10:00 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "5:00 PM", "5:30 PM")
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        days.forEach { day ->
            times.forEach { time ->
                val isAvailable = when (day) {
                    "Monday" -> false
                    "Tuesday" -> time in listOf("1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "5:00 PM", "5:30 PM")
                    else -> true
                }
                timeSlots.add(TimeSlot(day, time, isAvailable))
            }
        }
    }

    private fun setupTimeSlotRecyclerView() {
        val adapter = TimeSlotAdapter(timeSlots) { timeSlot, isSelected ->
            if (isSelected) {
                selectedTimeSlot = timeSlot
            } else {
                selectedTimeSlot = null
            }
            updateOrderButtonState()
        }

        timeSlotRecyclerView.layoutManager = GridLayoutManager(this, 3)
        timeSlotRecyclerView.adapter = adapter
    }

    private fun navigateToMentorSelection() {
        val intent = Intent(this, MentorConsultActivity::class.java)
        startActivity(intent)
    }

    private fun processOrder() {
        if (selectedTimeSlot == null) {
            Toast.makeText(this, "Please select a time slot.", Toast.LENGTH_SHORT).show()
        } else {
            // TODO: Proceed to payment or next step
            Toast.makeText(this, "Proceeding to payment...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateOrderButtonState() {
        orderNowButton.isEnabled = selectedTimeSlot != null
    }
}
